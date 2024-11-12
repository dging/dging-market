import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import ReactStars from 'react-stars';
import { Arrange, SpanBold } from '../Base';
import { ImgBtn, SmallBtn, IncludeImgBtn, DeclarationBtn } from '../Button';
import Test from '../../assets/images/Test.png';
import HeartEmpty from '../../assets/images/HeartEmpty.png';
import Time from '../../assets/images/Time.png';
import Eye from '../../assets/images/Eye.png';
import HeartFill from '../../assets/images/HeartFill.png';
import MessageFill from '../../assets/images/MessageFill.png';
import PayImmediately from '../../assets/images/PayImmediately.png';
import StoreManagement from '../../assets/images/StoreManagement.png';

const WrapStoreImage = styled.div<{ $bg?: string }>`
  width: 472px;
  height: 472px;
  border-radius: 16px;
  background-image: url(${(props) => props.$bg || null});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  color: ${({ theme }) => theme.color.black0};
`;

const FontBold24 = styled(Arrange)`
  ${({ theme }) => theme.font.b24};
`;

const Price = styled(Arrange)`
  ${({ theme }) => theme.font.price40};
  border-bottom: 1px solid ${({ theme }) => theme.color.black5};
`;

const Unit = styled(Arrange)`
  ${({ theme }) => theme.font.r24};
`;

const Bar = styled.div`
  width: 1px;
  height: 12px;
  background-color: ${({ theme }) => theme.color.black5};
`;

const ProfileBtn = styled.button<{ $bgcolor?: string; width?: string }>`
  display: flex;
  justify-content: center;
  width: ${(props) => props.width || '206px'};
  height: 64px;
  padding: 20px 10px;
  background-color: ${(props) => props.$bgcolor};
  border: 0;
  border-radius: 4px;
  cursor: pointer;
`;

export default function GoodsProfile() {
  const theme = useTheme();
  const [isOwner, setIsOwner] = useState<boolean>(false);

  return (
    <Arrange
      width={theme.page_size.width}
      height='472px'
      display='flex'
      margin='0 auto 50px auto'
      gap='50px'
    >
      {/* 왼쪽 이미지 */}
      <WrapStoreImage $bg={Test} />
      {/* 오른쪽 정보 */}
      <Arrange
        position='relative'
        width='638px'
        height='100%'
        padding={`${theme.size.l} 0`}
      >
        {/* 제목 & 가격 */}
        <FontBold24 width='100%' height='27px' margin='0 0 20px 0'>
          Test - Test
        </FontBold24>
        <Price
          width='100%'
          display='flex'
          alignitems='end'
          padding='0 0 30px 0'
          gap='4px'
        >
          <Arrange height='44px'>200000</Arrange>
          <Unit>원</Unit>
        </Price>
        {/* 찜 & 조회수 & 게시일 & 신고하기 버튼 */}
        <Arrange width='100%' margin='30px 0 40px 0'>
          <Arrange
            display='flex'
            width='100%'
            justifycontent='space-between'
            alignitems='center'
          >
            <Arrange display='flex' gap='10px' alignitems='center'>
              <IncludeImgBtn
                as='div'
                $leftbgimg={HeartEmpty}
                text='16'
                gap='4px'
                $textheight='20px'
                textstyle={{
                  color: theme.color.black2,
                  ...theme.font.info18_bold,
                }}
              />
              <Bar />
              <IncludeImgBtn
                as='div'
                $leftbgimg={Time}
                text='186'
                gap='4px'
                $textheight='20px'
                textstyle={{
                  color: theme.color.black2,
                  ...theme.font.info18_bold,
                }}
              />
              <Bar />
              <IncludeImgBtn
                as='div'
                $leftbgimg={Eye}
                text='5일 전'
                gap='4px'
                $textheight='20px'
                textstyle={{
                  color: theme.color.black2,
                  ...theme.font.info18_bold,
                }}
              />
            </Arrange>
            <Arrange>
              <DeclarationBtn />
            </Arrange>
          </Arrange>
        </Arrange>

        {/* 상품상태 & 배송비 & 직거래지역 & 희망장소 */}
        {/* api 붙이면 map으로 바꿀 예정 */}
        <Arrange width='100%'>
          <ul
            style={{
              padding: '0 0 0 30px',
              display: 'flex',
              flexDirection: 'column',
              gap: '20px',
              color: `${theme.color.black2}`,
              ...theme.font.info14,
            }}
          >
            <li style={{ height: '10px' }}>
              <Arrange display='flex' gap='50px'>
                <Arrange width='67px'>상품상태</Arrange>
                <Arrange
                  style={{
                    color: theme.color.black0,
                    ...theme.font.info14_bold,
                  }}
                >
                  사용감 적음
                </Arrange>
              </Arrange>
            </li>
            <li style={{ height: '10px' }}>
              <Arrange display='flex' gap='50px'>
                <Arrange width='67px'>배송비</Arrange>
                <Arrange
                  style={{
                    color: theme.color.black0,
                    ...theme.font.info14_bold,
                  }}
                >
                  무료배송
                </Arrange>
              </Arrange>
            </li>
            <li style={{ height: '10px' }}>
              <Arrange display='flex' gap='50px'>
                <Arrange width='67px'>직거래지역</Arrange>
                <Arrange
                  style={{
                    color: theme.color.black0,
                    ...theme.font.info14_bold,
                  }}
                >
                  서울특별시 강남구 논현1동
                </Arrange>
              </Arrange>
            </li>
            <li style={{ height: '10px' }}>
              <Arrange display='flex' gap='50px'>
                <Arrange width='67px'>희망장소</Arrange>
                <Arrange
                  style={{
                    color: theme.color.black0,
                    ...theme.font.info14_bold,
                  }}
                >
                  강남역 2번 출구
                </Arrange>
              </Arrange>
            </li>
          </ul>
        </Arrange>

        {/* 찜버튼 & 거래톡버튼 & 바로결제 */}
        <Arrange
          position='absolute'
          width='100%'
          display='flex'
          gap='10px'
          style={{ bottom: '20px' }}
        >
          {isOwner ? (
            <>
              <ProfileBtn $bgcolor={`${theme.color.pink100}`} width='100%'>
                <IncludeImgBtn
                  as='div'
                  bgcolor='transparent'
                  $leftbgimg={StoreManagement}
                  text='내 상점 관리'
                  textcolor='white'
                  textstyle={{ color: 'white', ...theme.font.info18 }}
                />
              </ProfileBtn>
            </>
          ) : (
            <>
              <ProfileBtn $bgcolor={`${theme.color.black1}`}>
                <IncludeImgBtn
                  as='div'
                  bgcolor='transparent'
                  $leftbgimg={HeartFill}
                  text={`찜 ${16}`}
                  textcolor='white'
                  textstyle={{ color: 'white', ...theme.font.info18 }}
                />
              </ProfileBtn>

              <ProfileBtn $bgcolor='#E59CE7'>
                <IncludeImgBtn
                  as='div'
                  bgcolor='transparent'
                  $leftbgimg={MessageFill}
                  text='거래톡'
                  textcolor='white'
                  textstyle={{ color: 'white', ...theme.font.info18 }}
                />
              </ProfileBtn>

              <ProfileBtn $bgcolor={`${theme.color.pink100}`}>
                <IncludeImgBtn
                  as='div'
                  bgcolor='transparent'
                  $leftbgimg={PayImmediately}
                  text='바로결제'
                  textcolor='white'
                  textstyle={{ color: 'white', ...theme.font.info18 }}
                />
              </ProfileBtn>
            </>
          )}
        </Arrange>
      </Arrange>
    </Arrange>
  );
}