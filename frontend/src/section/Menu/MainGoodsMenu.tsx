import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../../components/Base';
import { ImgBtn } from '../../components/Button';
import { BtnType } from '../../types/types';
import CD from '../../assets/images/CD.png';
import Cassette from '../../assets/images/Cassette.png';
import DVD from '../../assets/images/DVD.png';
import Vinyl from '../../assets/images/Vinyl.png';

const WrapMainGoodsMenu = styled(Arrange)`
  background-color: ${({ theme }) => theme.color.pink1};
`;

const Title = styled.div`
  ${({ theme }) => theme.font.b32}
  text-align: center;
`;
const Text = styled.div`
  ${({ theme }) => theme.font.r32}
  margin-top: 20px;
  text-align: center;
`;

const WrapImage = styled(ImgBtn)<{ $click?: boolean }>`
  box-sizing: border-box;
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  background-image: url(${(props) => props.$backgroundimage});
  background-size: contain;
  border: ${(props) =>
    props.$click
      ? `8px solid ${props.theme.color.pink2}`
      : `1px solid ${props.theme.color.black1}`};
  border-radius: 240px;
`;

const WrapMenuItem = styled.button`
  width: fit-content;
  height: fit-content;
  background-color: transparent;
  padding: 0;
  border: 0;
  cursor: pointer;
`;

export default function MainGoodsMenu() {
  const theme = useTheme();
  const navigate = useNavigate();
  const [isSelect, setIsSelect] = useState<string | null>(null);
  const items = ['CD', 'Vinyl', 'Cassette', 'DVD'];

  const onClickItem = (type: string) => {
    const params = new URLSearchParams({
      first: type,
    });
    setIsSelect(type);
    navigate(`/category?${params.toString()}`);
  };
  return (
    <WrapMainGoodsMenu
      display='flex'
      flexdirection='column'
      justifycontent='center'
      width='100%'
      padding={`${theme.size.xxxxxl} 0`}
      gap='74px'
    >
      <Title>어떤 상품을 찾으시나요?</Title>
      <Arrange width='100%' display='flex' justifycontent='center'>
        <Arrange
          width={theme.page_size.width}
          display='flex'
          justifycontent='space-between'
        >
          <WrapMenuItem
            onClick={() => {
              onClickItem('CD');
            }}
          >
            <WrapImage
              $click={isSelect === 'CD'}
              as='div'
              width='240px'
              height='240px'
              $backgroundimage={CD}
              borderradius='240px'
            />
            <Text>CD</Text>
          </WrapMenuItem>

          <WrapMenuItem onClick={() => onClickItem('Vinyl')}>
            <WrapImage
              $click={isSelect === 'Vinyl'}
              as='div'
              width='240px'
              height='240px'
              $backgroundimage={Vinyl}
            />
            <Text>Vinyl</Text>
          </WrapMenuItem>

          <WrapMenuItem onClick={() => onClickItem('Cassette')}>
            <WrapImage
              $click={isSelect === 'Cassette'}
              as='div'
              width='240px'
              height='240px'
              $backgroundimage={Cassette}
            />
            <Text>Cassette</Text>
          </WrapMenuItem>

          <WrapMenuItem onClick={() => onClickItem('DVD')}>
            <WrapImage
              $click={isSelect === 'DVD'}
              as='div'
              width='240px'
              height='240px'
              $backgroundimage={DVD}
            />
            <Text>DVD</Text>
          </WrapMenuItem>
        </Arrange>
      </Arrange>
    </WrapMainGoodsMenu>
  );
}
