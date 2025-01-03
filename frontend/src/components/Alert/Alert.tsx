import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange, Btn, IncludeImgBtn } from '../../components';
import { setState } from '../../utils/setState';
import { DownArrowBlack, NoAlert } from '../../assets/images';

const WrapAlert = styled(Arrange)`
  right: 0;
  top: 37px;
  background-color: white;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-top-left-radius: ${({ theme }) => theme.size.xxxxxs};
  border-bottom-right-radius: ${({ theme }) => theme.size.xxxxxs};
  border-bottom-left-radius: ${({ theme }) => theme.size.xxxxxs};
  z-index: 1;
`;

export default function Alert() {
  const [isShow, setIsShow] = useState<boolean>(false);
  const theme = useTheme();

  return (
    <div>
      <Arrange position='relative'>
        <IncludeImgBtn
          onClick={() => setState(!isShow, setIsShow)}
          text='알림'
          height='38px'
          $rightbgimg={DownArrowBlack}
          $rightimgwidth='16px'
          $rightimgheight='16px'
          mainstyle={{
            border: `1px solid ${theme.color.black1}`,
            borderRadius: '4px',
            padding: '11px 9px 9px 9px',
          }}
        />

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
