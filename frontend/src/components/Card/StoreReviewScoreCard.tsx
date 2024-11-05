import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import ReactStars from 'react-stars';
import { Arrange } from '../Base';

const WrapScoreCard = styled(Arrange)`
  border: 1px solid ${({ theme }) => theme.color.black5};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
`;

const WrapScore = styled(Arrange)`
  width: 430px;
  ${({ theme }) => theme.font.eb24}
`;

const Bar = styled.div`
  width: 1px;
  height: 46px;
  background-color: ${({ theme }) => theme.color.black5};
`;

const ReviewTitle = styled(Arrange)`
  ${({ theme }) => theme.font.info14};
  color: ${({ theme }) => theme.color.black2};
`;

export default function StoreReviewScoreCard() {
  const theme = useTheme();
  const [starScore, setStarScore] = useState(5);
  return (
    <WrapScoreCard
      width='100%'
      padding={theme.size.xl}
      display='flex'
      justifycontent='space-between'
    >
      <WrapScore display='flex' justifycontent='end'>
        <Arrange>
          <Arrange width='100%' textalign='center'>
            {starScore}
          </Arrange>
          <ReactStars value={starScore} size={20} edit={false} />
        </Arrange>
      </WrapScore>
      <Bar />
      <WrapScore>
        <Arrange>
          <Arrange width='100%' textalign='center' margin='0 0 3px 0'>
            100%
          </Arrange>
          <ReviewTitle width='100%' textalign='center'>
            만족후기
          </ReviewTitle>
        </Arrange>
      </WrapScore>
    </WrapScoreCard>
  );
}
