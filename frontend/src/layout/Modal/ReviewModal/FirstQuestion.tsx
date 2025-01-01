import React from 'react';
import { useTheme } from 'styled-components';
import ReactStars from 'react-stars';
import { useReviewModal } from '../../../recoil/reviewModal/useReviewModal';
import { Arrange, IncludeImgBtn } from '../../../components';
import { RightArrowGray } from '../../../assets/images';
import '../../../styles/starStyles.css';

export default function FirstQuestion(props: {
  setValue: React.Dispatch<React.SetStateAction<number>>;
}) {
  const { modalRate, setModalRate } = useReviewModal();
  const theme = useTheme();

  const onClickNextStep = () => {
    if (modalRate === 0) {
      alert('0점은 줄 수 없습니다.');
    } else {
      props.setValue(1);
    }
  };

  return (
    <>
      <ReactStars
        count={5}
        value={modalRate}
        size={30}
        color1='white'
        className='custom-star'
        onChange={(e) => {
          setModalRate(e);
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
          onClick={onClickNextStep}
        />
      </Arrange>
    </>
  );
}
