import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Btn from '../Button/Btn';
import ImgBtn from '../Button/ImgBtn';
import { BtnType, ArrangeType } from '../../types/types';
import Arrange from '../Base/Arrange';
import DownArrowBlack from '../../assets/images/DownArrowBlack.png';

type DropBoxType = BtnType & {
  items: Array<string>;
  type?: string | undefined;
  onClick?: (type?: string) => void;
};

type WrapItemType = ArrangeType & { $except?: boolean; $status?: boolean };

const DropBoxButton = styled(Btn)<BtnType>`
  box-sizing: border-box;

  width: ${(props) => props.width || '140px'};
  height: ${(props) => props.height || '36px'};
  padding: ${({ theme }) => theme.size.xxs};
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  border-bottom-left-radius: ${(props) => (props.$status ? '0px' : '4px')};
  border-bottom-right-radius: ${(props) => (props.$status ? '0px' : '4px')};

  text-align: right;
  ${({ theme }) => theme.font.r14};
`;

const DropBoxText = styled(Arrange)`
  width: calc(100% - 16px);
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
`;

const DropBoxBoard = styled(Arrange)<ArrangeType>`
  width: ${(props) => props.width || '140px'};
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-top: 1px solid white;
  border-bottom-left-radius: ${({ theme }) => theme.size.xxxxxs};
  border-bottom-right-radius: ${({ theme }) => theme.size.xxxxxs};
  background-color: white;
  top: 35px;
`;

const WrapItem = styled(Arrange)<WrapItemType>`
  padding: ${({ theme }) => theme.size.xxxs};
  color: ${(props) =>
    props.$status ? props.theme.color.black0 : props.theme.color.black2};

  text-align: center;
  ${({ theme }) => theme.font.r14};
  cursor: pointer;
`;

export default function DropBox(props: DropBoxType) {
  const navigate = useNavigate();
  const [items, setItems] = useState<string>(props.items[0]);
  const [isShow, setIsShow] = useState<boolean>(false);

  useEffect(() => {
    if (props.type !== undefined) {
      setItems(props.type);
    }
  }, []);

  const onClickItem = (type: string) => {
    setItems(type);
    setIsShow(false);

    if (props.onClick) {
      props.onClick(type);
    }
  };

  // 로직 다시 짜기

  return (
    <Arrange position='relative'>
      <DropBoxButton $status={isShow} onClick={() => setIsShow(!isShow)}>
        <Arrange display='flex' width='118px' justifycontent='space-between'>
          <DropBoxText>{items}</DropBoxText>
          <ImgBtn
            as='div'
            width='16px'
            height='16px'
            $backgroundimage={DownArrowBlack}
          />
        </Arrange>
      </DropBoxButton>
      {isShow && (
        <DropBoxBoard position='absolute' $status={isShow}>
          {props.items.map((value, index) => (
            <WrapItem
              width='100%'
              $status={items === value}
              key={index}
              onClick={() => {
                onClickItem(value);
              }}
            >
              {value}
            </WrapItem>
          ))}
        </DropBoxBoard>
      )}
    </Arrange>
  );
}
