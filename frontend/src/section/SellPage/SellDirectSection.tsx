import React, { useEffect } from 'react';
import styled, { useTheme } from 'styled-components';
import DaumPostcode from 'react-daum-postcode';
import { useSell } from '../../recoil/sell/useSell';
import { Arrange } from '../../components/Base';
import { RadioBtn } from '../../components/Button';
import { UnderlineTitle, BarTitle } from '../../components/Title';

const Wrap = styled.div<{ gap?: number }>`
  display: flex;
  flex-direction: column;
  gap: ${(props) => props.gap + 'px'};
`;

const WrapContent = styled.div`
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

const AddressBtn = styled.button`
  width: 122px;
  height: 60px;
  padding: 20px;
  background-color: white;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: 4px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.r18};
  cursor: pointer;
`;

const TitleInput = styled.input<{ width?: string; readOnly?: boolean }>`
  box-sizing: border-box;
  width: ${(props) =>
    props.width ? props.width : props.theme.page_size.width};
  height: 67px;
  padding: 20px;
  background-color: ${(props) =>
    props.readOnly ? props.theme.color.black3 : 'white'};
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: 4px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.body18};

  &::placeholder {
    color: ${({ theme }) => theme.color.black2};
  }

  &:focus {
    outline: none;
  }
`;

export default function SellDirectSection() {
  const SCRIPT_URL =
    '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js';
  const theme = useTheme();
  const { sellAddress, setSellAddress, sellDirect, setSellDirect } = useSell();

  useEffect(() => {
    const script = document.createElement('script');
    script.src = SCRIPT_URL;
    script.async = true;
    document.head.appendChild(script);

    return () => {
      document.head.removeChild(script);
    };
  }, []);

  const openPopup = () => {
    if (window.daum) {
      new window.daum.Postcode({
        oncomplete: function (data: any) {
          const newData = {
            zonecode: data.zonecode,
            address: data.address,
          };
          console.log(data);
          setSellAddress((prev) => ({ ...prev, ...newData }));
        },
      }).open();
    } else {
      alert('오류가 발생하였습니다.');
    }
  };

  return (
    <div>
      <Wrap gap={50}>
        <UnderlineTitle>추가정보</UnderlineTitle>

        <WrapContent>
          <BarTitle style={{ ...theme.font.r24 }}>직거래</BarTitle>
          <RadioBtn
            name='delivery'
            inputValue={['가능', '불가능']}
            setValue={setSellDirect}
          />
        </WrapContent>

        {sellDirect === '가능' && (
          <Arrange display='flex' flexdirection='column' gap='10px'>
            <Arrange display='flex' gap='10px'>
              <AddressBtn>내 위치</AddressBtn>
              <AddressBtn>최근 지역</AddressBtn>
              <AddressBtn onClick={openPopup}>주소 검색</AddressBtn>
            </Arrange>
            <TitleInput
              placeholder='지역을 설정해주세요.'
              value={sellAddress.address}
              readOnly
            />
            <TitleInput
              placeholder='거래 희망 장소를 입력해주세요.'
              value={sellAddress.detailAddress}
              onChange={(e) => {
                setSellAddress((prev) => ({
                  ...prev,
                  detailAddress: e.target.value,
                }));
              }}
            />
          </Arrange>
        )}
      </Wrap>
    </div>
  );
}
