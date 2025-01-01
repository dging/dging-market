import React, { useState, useCallback } from 'react';
import styled, { useTheme } from 'styled-components';
import { useSell } from '../../recoil/sell/useSell';
import {
  Arrange,
  BarTitle,
  RoundBtn,
  SellRoundCategory,
} from '../../components';
import { category1, category2, category3 } from '../../utils/_data';

const GrayRoundBtn = styled(RoundBtn)`
  border: 1px solid
    ${(props) =>
      props.$status ? props.theme.color.pink100 : props.theme.color.black2};
  color: ${(props) =>
    props.$status ? props.theme.color.pink100 : props.theme.color.black2};
`;

export const Wrap = styled.div<{ gap?: number }>`
  display: flex;
  flex-direction: column;
  gap: ${(props) => props.gap + 'px'};
`;

export const WrapContent = styled.div`
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

const Ring = styled.div`
  box-sizing: border-box;
  width: 10px;
  height: 10px;
  border: 3px solid ${({ theme }) => theme.color.pink100};
  border-radius: 50%;
`;
export default function SellCategorySection() {
  const theme = useTheme();
  const { sellCategory } = useSell();
  const [currentCategory, setCurrentCategory] = useState<{
    order: number;
    content: string;
  }>({
    order: 1,
    content: '',
  });

  const SelectCategory = useCallback(() => {
    // console.log(
    //   sellCategory,
    //   currentCategory,
    //   sellCategory[currentCategory.order]
    // );

    const checkData = () => {
      if (sellCategory[1] === '') {
        return { data: category1, order: 1 };
      } else if (currentCategory.order === 1) {
        return { data: category2, order: 2 };
      } else if (currentCategory.order === 2) {
        return { data: category3, order: 3 };
      } else {
        return { data: category3, order: 3 };
      }
    };

    return (
      <SellRoundCategory
        words={checkData().data}
        order={checkData().order}
        setValue={setCurrentCategory}
      />
    );
  }, [currentCategory]);

  return (
    <Wrap gap={50}>
      <WrapContent>
        <BarTitle style={{ ...theme.font.r24 }}>카테고리</BarTitle>

        <Arrange display='flex' alignitems='center' gap='10px'>
          <GrayRoundBtn
            $status={sellCategory[1] !== ''}
            style={{ ...theme.font.category18_bold }}
          >
            {sellCategory[1] || '선택중'}
          </GrayRoundBtn>

          {sellCategory[1] && (
            <>
              <Ring />
              <GrayRoundBtn
                $status={sellCategory[2] !== ''}
                style={{ ...theme.font.category18_bold }}
              >
                {sellCategory[2] || '선택중'}
              </GrayRoundBtn>
            </>
          )}
          {sellCategory[2] && (
            <>
              <Ring />
              <GrayRoundBtn
                $status={sellCategory[3] !== ''}
                style={{ ...theme.font.category18_bold }}
              >
                {sellCategory[3] || '선택중'}
              </GrayRoundBtn>
            </>
          )}
        </Arrange>
      </WrapContent>

      <WrapContent>
        <Arrange style={{ color: theme.color.black2, ...theme.font.r18 }}>
          &middot;{' '}
          {currentCategory.order === 3
            ? sellCategory[2]
            : sellCategory[currentCategory.order]}
          카테고리 선택
        </Arrange>

        <SelectCategory />
      </WrapContent>
    </Wrap>
  );
}
