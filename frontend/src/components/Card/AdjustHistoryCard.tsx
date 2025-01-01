import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange, ImgBtn } from '../../components';
import { Test } from '../../assets/images';

const Price = styled(Arrange)`
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.eb14_ls2};
`;

const Unit = styled.span`
  height: 14px;
  margin-left: 2px;
  ${({ theme }) => theme.font.b14_lh150};
`;

const Title = styled.div`
  width: fit-content;
  max-width: 200px;
  height: 20px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.body14};
`;

const Money = styled(Arrange)`
  display: flex;
  height: 15px;
  gap: 5px;
  align-items: center;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.r10_ls8};
`;

const Tag = styled.div`
  box-sizing: border-box;
  display: flex;
  width: 42px;
  height: 14px;
  margin: 3px 0 0 auto;
  padding: 1px 0 0 0;
  border: 1px solid ${({ theme }) => theme.color.pink100};
  border-radius: 15px;
  color: ${({ theme }) => theme.color.pink100};
  justify-content: center;
  align-items: center;
  font-family: 'NSRegular';
  font-size: 9px;
  letter-spacing: '-8%';
`;

export default function AdjustHistoryCard(props: {
  $rightbgimg?: string;
  status?: boolean;
  onChange?: React.ChangeEventHandler<HTMLInputElement>;
}) {
  const theme = useTheme();
  const [status, setStatus] = useState(false);
  const [text, setText] = useState('거래완료');

  return (
    <Arrange display='flex' width='560px' alignitems='center'>
      <Arrange width='100%' display='flex'>
        <ImgBtn
          as='div'
          width='62px'
          height='62px'
          $backgroundimage={Test}
          style={{ borderRadius: '4px' }}
        />
        <Arrange
          display='flex'
          flexdirection='column'
          width='498px'
          height='64px'
          padding='4px 15px 8px 15px'
        >
          <Arrange width='100%' display='flex' justifycontent='space-between'>
            <Title>Test - Test</Title>
            <Price height='20px' display='flex' alignitems='center'>
              <Arrange height='10px'>200,000</Arrange>
              <Unit>원</Unit>
            </Price>
          </Arrange>

          {props.status && (
            <Arrange width='100%' display='flex' justifycontent='space-between'>
              <Money>
                <Arrange>1232348498423839</Arrange>
                <Arrange>농협은행</Arrange>
              </Money>

              <Money>
                <Arrange>09.07</Arrange>
                <Arrange>입금완료</Arrange>
              </Money>
            </Arrange>
          )}

          <Tag>{props.status ? '안전거래' : '일반거래'}</Tag>
        </Arrange>
      </Arrange>
    </Arrange>
  );
}
