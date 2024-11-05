import { DefaultTheme } from 'styled-components';

const color = {
  pink100: '#E61FEA',
  pink60: '#F079F2',
  pink40: '#F5A5F7',
  pink20: '#FAD2FB',
  pink1: '#F6E9FF',
  pink2: '#E59CE7',
  black0: '#282828',
  black1: '#D1D3D8',
  black2: '#868B94',
  black3: '#F2F3F6',
  black4: '#F9F9F9',
  black5: '#EAEBEE',
};

const size = {
  xxxxxs: '4px',
  xxxxs: '6px',
  xxxs: '8px',
  xxs: '10px',
  xs: '12px',
  s: '14px',
  m: '16px',
  l: '18px',
  xl: '20px',
  xxl: '24px',
  xxxl: '32px',
  xxxxl: '40px',
  xxxxxl: '50px',
  xxxxxxl: '100px',
};

const page_size = {
  width: '1160px',
  minwidth: '1200px',
};

const font = {
  body_12: {
    fontFamily: 'NSRegular',
    fontSize: '12px',
    lineHeight: '150%',
  },
  body_12_bold: {
    fontFamily: 'NSBold',
    fontSize: '12px',
    lineHeight: '150%',
  },
  body14: {
    fontFamily: 'NSRegular',
    fontSize: '14px',
    lineHeight: '150%',
  },
  body16_sparse: {
    fontFamily: 'NSRegular',
    fontSize: '16px',
    lineHeight: '150%',
    letterSpacing: '6%',
  },
  body16: {
    fontFamily: 'NSRegular',
    fontSize: '16px',
    lineHeight: '150%',
  },
  body18: {
    fontFamily: 'NSRegular',
    fontSize: '18px',
    lineHeight: '150%',
  },
  info14: {
    fontFamily: 'NSRegular',
    fontSize: '14px',
    letterSpacing: '-1.28px',
  },

  info12: {
    fontFamily: 'NSRegular',
    fontSize: '12px',
    letterSpacing: '-0.96px',
  },
  ac12: {
    FontFamily: 'NSRegular',
    fontSize: '12px',
    letterSpacing: '-0.96px',
  },
  category16: {
    fontFamily: 'NSRegular',
    fontSize: '16px',
    letterSpacing: '-1.6px',
  },
  price18: {
    fontFamily: 'NSBold',
    fontSize: '18px',
    letterSpacing: '-0.36px',
  },
  date14: {
    fontFamily: 'NSRegular',
    fontSize: '14px',
    letterSpacing: '-1.12px',
  },
  r12: {
    fontFamily: 'NSRegular',
    fontSize: '12px',
  },
  r14: {
    fontFamily: 'NSRegular',
    fontSize: '14px',
  },
  r16: {
    fontFamily: 'NSRegular',
    fontSize: '16px',
  },
  r18: {
    fontFamily: 'NSRegular',
    fontSize: '18px',
  },
  r32: {
    fontFamily: 'NSRegular',
    fontSize: '32px',
  },
  b12: {
    fontFamily: 'NSBold',
    fontSize: '12px',
  },
  b14: {
    fontFamily: 'NSBold',
    fontSize: '14px',
  },
  b18: {
    fontFamily: 'NSBold',
    fontSize: '18px',
  },
  b24: {
    fontFamily: 'NSBold',
    fontSize: '24px',
  },
  b32: {
    fontFamily: 'NSBold',
    fontSize: '32px',
  },
  eb24: {
    fontFamily: 'NSExtraBold',
    fontSize: '24px',
  },
};

export const theme: DefaultTheme = {
  color,
  size,
  page_size,
  font,
};
