import React from 'react';
import { useTheme } from 'styled-components';
import ReactStars from 'react-stars';
import { useReviewModal } from '../../recoil/reviewModal/useReviewModal';
import { Arrange, IncludeImgBtn } from '../../components';
import { RightArrowGray } from '../../assets/images';
import '../../styles/starStyles.css';

export default function FirstQuestion(props: {
  setValue: React.Dispatch<React.SetStateAction<number>>;
}) {
  const { modalInfo, setModalInfo, consoleModalInfo } = useReviewModal();
  const theme = useTheme();

  return (
    <>
      <ReactStars
        count={5}
        value={modalInfo.rate}
        size={30}
        color1='white'
        className='custom-star'
        onChange={(e) => {
          setModalInfo({ ...modalInfo, rate: e });
        }}
      />
      <Arrange width='100%' display='flex' margin='40px 0 0 0'>
        <IncludeImgBtn
          text='다음'
          textstyle={{
            color: theme.color.black2,
            ...theme.font.r16,
          }}
          $rightbgimg={RightArrowGray}
          $rightimgwidth='20px'
          $rightimgheight='20px'
          gap='0px'
          mainstyle={{ marginLeft: 'auto' }}
          onClick={() => {
            props.setValue(1);
            consoleModalInfo();
          }}
        />
      </Arrange>
    </>
  );
}
