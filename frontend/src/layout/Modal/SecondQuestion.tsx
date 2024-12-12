import React, { useState } from 'react';
import { useTheme } from 'styled-components';
import { Arrange, CheckBox, IncludeImgBtn } from '../../components';
import {
  LeftArrowGray,
  RightArrowGray,
  CheckBorderGray,
  CheckBorderPink,
} from '../../assets/images';

export default function SecondQuestion(props: {
  setValue: React.Dispatch<React.SetStateAction<number>>;
}) {
  const theme = useTheme();

  const [checkList, setCheckList] = useState([
    { content: '😏 구매확정이 빨라요.', value: false },
    { content: '😎 거래톡 답변이 빨라요.', value: false },
    { content: '😇 친절하고 배려가 넘쳐요.', value: false },
    { content: '🤩 무리한 네고를 하지 않아요.', value: false },
    { content: '☺️ 꼭 필요한 문의만 해요.', value: false },
    { content: '선택할 항목이 없어요.', value: false },
  ]);

  const onChangeCheckBox = (key: number) => {
    setCheckList(
      checkList.map((val, idx) => {
        if (key === 5 && val.value === false) {
          // 마지막이 false일 때 누르면 나머지 모든 값이 false로 되고 마지막만 true로 되게
          console.log('check1');
          console.log('before:', checkList, key, idx);
          return { ...val, value: idx === key };
        } else if (key === 5 && val.value === true) {
          // 마지막이 true일 때 다른 버튼 누르면 마지막 false되고 나머지 true로 되게
          console.log('check2');
          console.log('before:', checkList, key, idx);
          return { ...val, value: false };
        }
        // 클릭 시 true, false 왔다갔다
        return idx === key ? { ...val, value: !val.value } : val;
      })
    );

    console.log('after:', checkList);
  };

  const CheckBoxList = (props: {
    content: string;
    value: boolean;
    num: number;
  }) => {
    return (
      <Arrange width='100%' display='flex'>
        <CheckBox
          width='18px'
          height='18px'
          value={props.value}
          $bgimg={props.value ? CheckBorderPink : CheckBorderGray}
          onChange={() => onChangeCheckBox(props.num)}
        />
        <Arrange
          width='100%'
          style={{ marginLeft: '7px', ...theme.font.body14 }}
        >
          {props.content}
        </Arrange>
      </Arrange>
    );
  };

  return (
    <>
      <Arrange
        width='100%'
        margin='0 0 20px'
        style={{ color: theme.color.black2, ...theme.font.body14 }}
      >
        해당하는 항목을 모두 골라주세요.
      </Arrange>
      <Arrange
        width='100%'
        display='flex'
        flexdirection='column'
        gap='5px'
        margin='0 0 20px 0'
      >
        {checkList.map((val, idx) => (
          <CheckBoxList
            content={val.content}
            value={val.value}
            key={idx}
            num={idx}
          />
        ))}
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
          onClick={() => props.setValue(0)}
        />

        <IncludeImgBtn
          text='다음'
          textstyle={{
            marginTop: '2px',
            color: theme.color.black2,
            ...theme.font.r16,
          }}
          $rightbgimg={RightArrowGray}
          $rightimgwidth='20px'
          $rightimgheight='20px'
          gap='0px'
          onClick={() => {
            props.setValue(2);
            console.log('checkList : ', checkList);
          }}
        />
      </Arrange>
    </>
  );
}
