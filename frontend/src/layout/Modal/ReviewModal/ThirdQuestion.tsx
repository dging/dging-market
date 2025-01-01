import React from 'react';
import styled, { useTheme } from 'styled-components';
import { useReviewModal } from '../../../recoil/reviewModal/useReviewModal';
import { AddImage, Arrange, IncludeImgBtn } from '../../../components';
import { LeftArrowGray, RightArrowPink } from '../../../assets/images';

const DescriptionTextArea = styled.textarea`
  box-sizing: border-box;
  width: 100%;
  height: 220px;
  padding: 20px 20px 56px;
  color: ${({ theme }) => theme.color.black0};
  border: 1px solid ${({ theme }) => theme.color.black5};
  border-radius: 4px;
  resize: none;
  ${({ theme }) => theme.font.body14};

  &:focus {
    outline: none;
  }
`;

const CountWords = styled.div`
  position: absolute;
  width: fit-content;
  height: fit-content;
  bottom: 20px;
  right: 20px;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.r14};
`;

export default function ThirdQuestion(props: {
  setValue: React.Dispatch<React.SetStateAction<number>>;
}) {
  const theme = useTheme();
  const {
    modalDetailReview,
    setModalDetailReview,
    modalPhotoReview,
    setModalPhotoReview,
  } = useReviewModal();
  return (
    <>
      <AddImage />
      <Arrange
        margin='20px 0 10px 0'
        style={{ color: theme.color.black2, ...theme.font.info12 }}
      >
        여러분의 후가가 더 나은 중고거래 환경을 만들어가는데 보템이 됩니다.
        (선택사항)
      </Arrange>
      <Arrange position='relative' width='100%' margin='0 0 20px 0'>
        <DescriptionTextArea
          maxLength={200}
          placeholder='후기를 작성해주세요.'
          value={modalDetailReview}
          onChange={(e) => {
            setModalDetailReview(e.target.value);
          }}
        />
        <CountWords>{modalDetailReview.length} / 200</CountWords>
      </Arrange>
      <Arrange width='100%' display='flex' justifycontent='space-between'>
        <IncludeImgBtn
          $leftbgimg={LeftArrowGray}
          $leftimgwidth='20px'
          $leftimgheight='20px'
          text='이전'
          textstyle={{
            marginTop: '2px',
            color: theme.color.black2,
            ...theme.font.r16,
          }}
          gap='0px'
          onClick={() => props.setValue(1)}
        />

        <IncludeImgBtn
          text='등록'
          textstyle={{
            marginTop: '2px',
            color: theme.color.pink100,
            ...theme.font.r16,
          }}
          $rightbgimg={RightArrowPink}
          $rightimgwidth='20px'
          $rightimgheight='20px'
          gap='0px'
          onClick={() => props.setValue(3)}
        />
      </Arrange>
    </>
  );
}
