import ReactStars from 'react-stars';
import React from 'react';
import styled, { useTheme } from 'styled-components';

import { Arrange } from '../Base';
import { ImgBtn } from '../Button';
import Test from '../../assets/images/Test.png';

const WrapStoreProfile = styled(Arrange)`
  border-radius: 16px;
  overflow: hidden;
`;

const WrapStoreImage = styled.div<{ $bg?: string; $bgsize?: string }>`
  width: 380px;
  height: 380px;

  /* background-image: url('../../assets/images/Test.png'); */
  background-image: url(${(props) => props.$bg || null});
  background-position: 120%;
  background-size: contain;
  background-repeat: no-repeat;
  filter: blur(20px);
`;

export default function StoreProfile() {
  const theme = useTheme();
  const ratingChanged = (newRating: number) => {
    console.log(newRating);
  };
  return (
    <Arrange width={theme.page_size.width} display='flex'>
      <WrapStoreProfile>
        <WrapStoreImage $bg={Test} />
      </WrapStoreProfile>
      <Arrange width='780px' padding={`${theme.size.xl} ${theme.size.xxxxxl}`}>
        <ReactStars
          count={5}
          value={2.5}
          //   size={30}
          edit={false}
          onChange={ratingChanged}
          color2={'#ffd700'}
        />
      </Arrange>
    </Arrange>
  );
}
