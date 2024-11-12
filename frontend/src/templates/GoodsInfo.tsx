import React from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../components/Base';
import { IncludeImgBtn } from '../components/Button';
import { BarTitle } from '../components/Title';
import Locate from '../assets/images/Locate.png';
import NoteBook from '../assets/images/NoteBook.png';
import Tag from '../assets/images/Tag.png';

const Bar = styled.div`
  width: 1px;
  height: 74px;
  margin: 8px 0 0 0;
  background-color: ${({ theme }) => theme.color.black5};
`;

function GoodsInfo() {
  const theme = useTheme();
  return (
    <Arrange
      display='flex'
      flexdirection='column'
      gap='50px'
      padding='0 20px 0 0'
    >
      <BarTitle title='상품정보' style={{ ...theme.font.r18 }} />
      <Arrange width='747px' style={{ ...theme.font.body14 }}>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non risus
        pharetra, egestas orci eget, suscipit nisi. Duis est arcu, tempus non
        sem eu, vestibulum pharetra odio. Sed eget ligula vehicula, scelerisque
        lorem ac, ullamcorper nibh. Maecenas maximus consequat metus, sed
        elementum ante sodales non. Cras dictum lectus massa, sed congue magna
        ultrices vel. Integer mauris nibh, rhoncus eget sodales a, posuere
        faucibus tortor. Donec convallis pulvinar lacus nec sodales. In hac
        habitasse platea dictumst. Etiam arcu purus, molestie eu eros nec,
        ultricies sagittis nunc. Maecenas volutpat, massa non interdum accumsan,
        ex magna tincidunt arcu, ut finibus magna lorem eget odio. Sed posuere
        ornare sapien, in interdum sapien sodales at. Pellentesque nec tempus
        justo.
        <br />
        <br />
        Quisque hendrerit risus dolor, in mattis tortor viverra ut. Vestibulum
        scelerisque porta turpis, vitae aliquet magna congue id. Donec ultricies
        quam ac magna malesuada, eget semper massa semper. Nunc malesuada
        vestibulum arcu, nec rutrum arcu consequat ac. Etiam at sapien purus.
        Quisque bibendum nibh ut mauris ornare lacinia. Donec at tellus velit.
        In hac habitasse platea dictumst. Sed massa lorem, aliquam non gravida
        et, pellentesque vitae sapien. Donec non sem id quam accumsan malesuada
        id eget nisi. Fusce a orci hendrerit, accumsan metus ac, rhoncus leo.
        Morbi sit amet tortor posuere, ultricies lectus et, varius massa.
        Praesent auctor diam vestibulum faucibus consectetur. Donec congue eros
        tincidunt lectus rhoncus pulvinar. Donec id pharetra urna, ac dapibus
        nisl.
        <br />
        <br />
        Quisque hendrerit risus dolor, in mattis tortor viverra ut. Vestibulum
        scelerisque porta turpis, vitae aliquet magna congue id. Donec ultricies
        quam ac magna malesuada, eget semper massa semper. Nunc malesuada
        vestibulum arcu, nec rutrum arcu consequat ac. Etiam at sapien purus.
        Quisque bibendum nibh ut mauris ornare lacinia. Donec at tellus velit.
        In hac habitasse platea dictumst. Sed massa lorem, aliquam non gravida
        et, pellentesque vitae sapien. Donec non sem id quam accumsan malesuada
        id eget nisi. Fusce a orci hendrerit, accumsan metus ac, rhoncus leo.
        Morbi sit amet tortor posuere, ultricies lectus et, varius massa.
        Praesent auctor diam vestibulum faucibus consectetur. Donec congue eros
        tincidunt lectus rhoncus pulvinar. Donec id pharetra urna, ac dapibus
        nisl.
        <br />
        <br />
        Quisque hendrerit risus dolor, in mattis tortor viverra ut. Vestibulum
        scelerisque porta turpis, vitae aliquet magna congue id. Donec ultricies
        quam ac magna malesuada, eget semper massa semper. Nunc malesuada
        vestibulum arcu, nec rutrum arcu consequat ac. Etiam at sapien purus.
        Quisque bibendum nibh ut mauris ornare lacinia. Donec at tellus velit.
        In hac habitasse platea dictumst. Sed massa lorem, aliquam non gravida
        et, pellentesque vitae sapien. Donec non sem id quam accumsan malesuada
        id eget nisi. Fusce a orci hendrerit, accumsan metus ac, rhoncus leo.
        Morbi sit amet tortor posuere, ultricies lectus et, varius massa.
        Praesent auctor diam vestibulum faucibus consectetur. Donec congue eros
        tincidunt lectus rhoncus pulvinar. Donec id pharetra urna, ac dapibus
        nisl.
      </Arrange>

      <Arrange
        display='flex'
        width='100%'
        padding='20px 10px 30px 10px'
        gap='10px'
        style={{
          borderTop: `1px solid ${theme.color.black5}`,
          borderBottom: `1px solid ${theme.color.black5}`,
        }}
      >
        <Arrange
          width='229px'
          display='flex'
          padding='10px 0'
          flexdirection='column'
          alignitems='center'
          gap='20px'
        >
          <IncludeImgBtn
            as='div'
            $leftbgimg={Locate}
            text='직거래지역'
            textstyle={{ color: theme.color.black1, ...theme.font.info14 }}
          />
          <Arrange
            textalign='center'
            style={{
              color: theme.color.black2,
              ...theme.font.font14_bold,
            }}
          >
            서울특별시 강남구 논현1동
            <br />
            (강남역 2번 출구)
          </Arrange>
        </Arrange>

        <Bar />

        <Arrange
          width='229px'
          display='flex'
          padding='10px 0'
          flexdirection='column'
          alignitems='center'
          gap='20px'
        >
          <IncludeImgBtn
            as='div'
            $leftbgimg={NoteBook}
            text='카테고리'
            textstyle={{ color: theme.color.black1, ...theme.font.info14 }}
          />
          <Arrange
            textalign='center'
            style={{
              color: theme.color.black2,
              ...theme.font.font14_bold,
            }}
          >
            CD &gt; Rock &gt; Punk
          </Arrange>
        </Arrange>

        <Bar />

        <Arrange
          width='229px'
          display='flex'
          padding='10px 0'
          flexdirection='column'
          alignitems='center'
          gap='20px'
        >
          <IncludeImgBtn
            as='div'
            $leftbgimg={Tag}
            text='상품태그'
            textstyle={{ color: theme.color.black1, ...theme.font.info14 }}
          />
          <Arrange
            textalign='center'
            style={{
              color: theme.color.black2,
              ...theme.font.font14_bold,
            }}
          >
            #CD #Rock #Punk
          </Arrange>
        </Arrange>
      </Arrange>
    </Arrange>
  );
}

export default GoodsInfo;
