import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange, Btn } from '../../components';
import { setState } from '../../utils/setState';
import { DownArrowBlack, NoAlert } from '../../assets/images';

const WrapAlert = styled(Arrange)`
  right: 0;
  top: 35px;

  background-color: white;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-top-left-radius: ${({ theme }) => theme.size.xxxxxs};
  border-bottom-right-radius: ${({ theme }) => theme.size.xxxxxs};
  border-bottom-left-radius: ${({ theme }) => theme.size.xxxxxs};
  z-index: 1;
`;

const AlertBtn = styled(Btn)<{ $open?: boolean }>`
  height: 36px;
  border-bottom-left-radius: ${(props) =>
    props.$open ? '0px' : props.theme.color.black1};
  border-bottom-right-radius: ${(props) =>
    props.$open ? '0px' : props.theme.color.black1};
  border-bottom: 1px solid
    ${(props) => (props.$open ? 'white' : props.theme.color.black1)};
  z-index: 99;
`;

export default function Alert() {
  const [isShow, setIsShow] = useState<boolean>(false);
  const theme = useTheme();

  return (
    <div>
      <Arrange position='relative'>
        <AlertBtn onClick={() => setState(!isShow, setIsShow)} $open={isShow}>
          <Arrange display='flex' alignitems='center'>
            알람
            <Arrange
              width={`${theme.size.m}`}
              height={`${theme.size.m}`}
              margin='0 0 0 4px'
              $backgroundimage={DownArrowBlack}
            />
          </Arrange>
        </AlertBtn>

        {isShow && (
          <WrapAlert
            position='absolute'
            width='400px'
            height='300px'
            display='flex'
            justifycontent='center'
            alignitems='center'
          >
            <Arrange
              width='300px'
              height='48px'
              $backgroundimage={NoAlert}
            ></Arrange>
          </WrapAlert>
        )}
      </Arrange>
    </div>
  );
}
