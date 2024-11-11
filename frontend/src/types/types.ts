import { CSS } from 'styled-components/dist/types';

export interface BtnType {
  as?: string;
  position?: string;
  margin?: string;
  children?: React.ReactNode;
  width?: string;
  height?: string;
  borderradius?: string;
  $status?: boolean;
  $backgroundimage?: string;
  $backgroundcolor?: any;
  $backgroundposition?: string;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

export interface BtnButtonType {
  as?: string;
  width?: string;
  height?: string;
  $status?: boolean;
  $backgroundimage?: string;
  $backgroundposition?: string;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

export interface ArrangeType {
  as?: string;
  position?: string;
  display?: string;
  width?: any;
  minwidth?: any;
  height?: any;
  margin?: string;
  padding?: any;
  flexdirection?: string;
  justifycontent?: string;
  alignitems?: string;
  gap?: string;
  textalign?: string;
  children?: React.ReactNode;
  $status?: boolean;
  $top?: boolean;
  $bottom?: boolean;
  $backgroundimage?: any;
  $backgroundposition?: string;
  style?: React.CSSProperties;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

export interface IncludeImgBtnType {
  as?: string;
  width?: string;
  height?: string;
  $textwidth?: string;
  $textheight?: string;
  $status?: any;
  gap?: string;
  $change?: boolean;
  text?: string;
  textcolor?: string;
  fontsize?: string;
  font?: string;
  textstyle?: CSS.Properties;
  bgcolor?: string;
  style?: React.CSSProperties;
  mainstyle?: React.CSSProperties;
  onClick?: any;
}

export interface RightImgType {
  $rightimgwidth?: string;
  $rightimgheight?: string;
  $rightbgimg?: any;
  $rightbgchangeimg?: any;
  $rightbgposition?: string;
  $change?: boolean;
}

export interface LeftImgType {
  $leftimgwidth?: string;
  $leftimgheight?: string;
  $leftbgimg?: any;
  $leftbgchangeimg?: any;
  $leftbgposition?: string;
  $change?: boolean;
}
