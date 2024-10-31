import { DefaultTheme } from 'styled-components';

const color = {
  pink100: '#E61FEA',
  pink60: '#F079F2',
  pink40: '#F5A5F7',
  pink20: '#FAD2FB',
  pink1: '#F6E9FF',
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
  width_s: '1160px',
};

const font = {
  body_12: {
    fontFamily: 'NSRegular',
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
  category16: {
    fontFamily: 'NSRegular',
    fontSize: '16px',
    letterSpace: '-1.6px',
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
  b12: {
    fontFamily: 'NSBold',
    fontSize: '12px',
  },
  b14: {
    fontFamily: 'NSBold',
    fontSize: '14px',
  },
};

export const theme: DefaultTheme = {
  color,
  size,
  page_size,
  font,
};
