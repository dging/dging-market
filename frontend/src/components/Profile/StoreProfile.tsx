import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import ReactStars from 'react-stars';
import { Arrange, SpanBold } from '../Base';
import { ImgBtn, SmallBtn, DeclarationBtn } from '../Button';
import Test from '../../assets/images/Test.png';
import UserPlusWhite from '../../assets/images/UserPlusWhite.png';
import Verification from '../../assets/images/Verification.png';

const WrapStoreProfile = styled(Arrange)`
  position: relative;
  border-radius: 16px;
  overflow: hidden;
`;

const WrapStoreImage = styled.div<{ $bg?: string; $bgsize?: string }>`
  width: 380px;
  height: 380px;
  background-image: url(${(props) => props.$bg || null});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  filter: blur(20px);
`;

const WrapLeftDetail = styled(Arrange)`
  color: white;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
`;

const StoreImage = styled.div<{ $bg?: string }>`
  width: 128px;
  height: 128px;
  margin: 0 auto 15px auto;
  border-radius: 128px;
  background-image: url(${(props) => props.$bg || null});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
`;

const LeftTitle = styled(Arrange)`
  ${({ theme }) => theme.font.b24}
  margin-bottom: 10px;
`;

const Info = styled(Arrange)`
  ${({ theme }) => theme.font.r12};
  margin-top: 4px;
`;

const ManageBtn = styled.button<{ $status?: boolean }>`
  width: ${(props) => (props.$status ? '144px' : '134px')};
  height: 50px;
  margin: 0 auto;
  border: 1px solid white;
  display: flex;
  justify-content: center;
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  background-color: transparent;
  padding: ${(props) =>
    props.$status ? `${props.theme.size.s} 30px` : `12px 30px 20px 30px`};
  color: white;
  ${({ theme }) => theme.font.r16};
  cursor: pointer;
`;

const RightTitle = styled.input<{ as?: string }>`
  width: ${(props) => (props.as === 'div' ? 'fit-content' : '360px')};
  height: 28px;
  padding: 0;
  border: none;
  ${({ theme }) => theme.font.b24}
  &:focus {
    outline: none;
  }
`;

const RightDetail = styled(Arrange)`
  ${({ theme }) => theme.font.info14};
  color: ${({ theme }) => theme.color.black2};
  border-bottom: 1px solid ${({ theme }) => theme.color.black5};
`;

const WrapBlack = styled(Arrange)`
  font-family: 'NSBold';
  color: ${({ theme }) => theme.color.black0};
`;

const IntroInput = styled.textarea`
  box-sizing: border-box;
  width: 100%;
  height: 160px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.body14};
  border: none;
  resize: none;
  &:focus {
    outline: none;
  }
`;

const WrapGray = styled(Arrange)`
  ${({ theme }) => theme.font.ac12};
  color: ${({ theme }) => theme.color.black2};
`;

const CountWord = styled(Arrange)`
  ${({ theme }) => theme.font.info12};
  color: ${({ theme }) => theme.color.black0};
`;

export default function StoreProfile() {
  const theme = useTheme();
  const [isOwner, setIsOwner] = useState(false);
  const [isAuth, setIsAuth] = useState(true);
  const [titleEdit, setTitleEdit] = useState(true);
  const [titleValue, setTitleValue] = useState('TEST');
  const [introEdit, setIntroEdit] = useState(true);
  const [introValue, setIntroValue] = useState('안녕하세요.');
  const ratingChanged = (newRating: number) => {
    console.log(newRating);
  };

  const KeyEvent1 = (e: any) => {
    console.log('shiftKey : ', e.shiftKey, 'keyCode : ', e.keyCode);
    if (e.keyCode === 13) {
      console.log('submit');
    } else if (e.keyCode === 27) {
      setTitleEdit(!titleEdit);
    }
  };

  const KeyEvent2 = (e: any) => {
    console.log('shiftKey : ', e.shiftKey, 'keyCode : ', e.keyCode);
    if (e.keyCode === 13 && e.shiftKey === false) {
      console.log('submit');
    } else if (e.keyCode === 13 && e.shiftKey === true) {
      console.log(e.shiftKey);
      console.log('new line');
    } else if (e.keyCode === 27) {
      setIntroEdit(!introEdit);
    }
  };

  return (
    <Arrange
      width={theme.page_size.width}
      display='flex'
      padding={`30px 0 ${theme.size.xxxxxl} 0`}
      margin='0 auto'
    >
      {/* 왼쪽 이미지 */}
      <WrapStoreProfile>
        <WrapStoreImage $bg={Test} />
        <WrapLeftDetail
          position='absolute'
          display='flex'
          width='280px'
          height='290px'
          flexdirection='column'
        >
          <StoreImage $bg={Test} />
          <LeftTitle width='100%' textalign='center'>
            {titleValue}
          </LeftTitle>
          <Arrange width='100%' display='flex' justifycontent='center'>
            <ReactStars value={5} count={5} size={20} edit={false} />
          </Arrange>
          {!isOwner && (
            <>
              <Info width='100%' textalign='center'>
                상품 <SpanBold>27553</SpanBold> &nbsp;| &nbsp;팔로워
                <SpanBold>23312</SpanBold>
              </Info>
            </>
          )}
          <Arrange width='100%' textalign='center' margin='16px 0 0 0'>
            {isOwner ? (
              <>
                <ManageBtn $status={isOwner}>내 상품 관리</ManageBtn>
              </>
            ) : (
              <>
                <ManageBtn $status={isOwner}>
                  <ImgBtn as='div' $backgroundimage={UserPlusWhite} />
                  <Arrange padding='4px 0 0 4px'>팔로우</Arrange>
                </ManageBtn>
              </>
            )}
          </Arrange>
        </WrapLeftDetail>
      </WrapStoreProfile>
      {/* 오른쪽 부분 */}
      <Arrange width='780px' padding={`${theme.size.l} ${theme.size.xxxxxl}`}>
        <Arrange
          display='flex'
          justifycontent='space-between'
          width='100%'
          $bottom={true}
          padding={`0 0 ${theme.size.xl} 0`}
        >
          {titleEdit ? (
            <>
              <Arrange display='flex' gap='10px' alignitems='center'>
                <RightTitle
                  as='div'
                  value={titleValue}
                  readOnly={titleEdit}
                  onKeyDown={KeyEvent1}
                >
                  {titleValue}
                </RightTitle>
                {isOwner && (
                  <SmallBtn onClick={() => setTitleEdit(!titleEdit)}>
                    상품명 수정
                  </SmallBtn>
                )}
              </Arrange>
            </>
          ) : (
            <>
              <RightTitle
                as='input'
                value={titleValue}
                readOnly={titleEdit}
                onKeyDown={KeyEvent1}
                onChange={(e) => setTitleValue(e.target.value)}
              />
              <WrapGray>
                수정을 완료하시려면 Enter 입력해주세요.
                <br />
                이전으로 돌아가시려면 ESC를 입력해주세요.
              </WrapGray>
            </>
          )}

          {isAuth && (
            <ImgBtn as='div' width='83px' $backgroundimage={Verification} />
          )}
        </Arrange>
        <RightDetail
          width='100%'
          padding={`${theme.size.xl} 0`}
          display='flex'
          gap='10px'
        >
          상점오픈일<WrapBlack>2598 일 전</WrapBlack>
          <div>|</div>상품판매
          <WrapBlack>4회</WrapBlack>
        </RightDetail>
        <Arrange width='100%' padding={`${theme.size.xl} 0`}>
          <IntroInput
            value={introValue}
            readOnly={introEdit}
            onChange={(e: any) => setIntroValue(e.target.value)}
            onKeyDown={KeyEvent2}
            maxLength={1000}
          />
        </Arrange>
        <Arrange
          width='100%'
          display='flex'
          justifycontent='space-between'
          alignitems='flex-end'
        >
          {isOwner ? (
            <>
              {introEdit ? (
                <>
                  <SmallBtn onClick={() => setIntroEdit(!introEdit)}>
                    소개글 수정
                  </SmallBtn>
                </>
              ) : (
                <>
                  <WrapGray>
                    수정을 완료하시려면 Enter, 이전으로 돌아가시려면 Esc를
                    입력해주세요.
                    <br />
                    줄바꿈은 Shift + Enter ( ⇧ + ⏎ )
                  </WrapGray>
                  <CountWord>{introValue.length} / 1000</CountWord>
                </>
              )}
            </>
          ) : (
            <DeclarationBtn />
          )}
        </Arrange>
      </Arrange>
    </Arrange>
  );
}
