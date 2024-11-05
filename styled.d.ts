import 'styled-components';

declare module 'react-star-rating-component';
declare module 'react-rating-stars-component';

declare module 'styled-components' {
  export interface DefaultTheme {
    color: {
      pink100: string;
      pink60: string;
      pink40: string;
      pink20: string;
      pink1: string;
      pink2: string;
      black0: string;
      black1: string;
      black2: string;
      black3: string;
      black4: string;
      black5: string;
      [key: string]: string;
    };
    size: {
      xxxxxs: string;
      xxxxs: string;
      xxxs: string;
      xxs: string;
      xs: string;
      s: string;
      m: string;
      l: string;
      xl: string;
      xxl: string;
      xxxl: string;
      xxxxl: string;
      xxxxxl: string;
      xxxxxxl: string;
      [key: string]: string;
    };
    page_size: {
      width: string;
      [key: string]: string;
    };
  }
}
