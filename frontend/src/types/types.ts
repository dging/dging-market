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
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

export interface IncludeImgBtnType {
  width?: string;
  height?: string;
  $textwidth?: string;
  $textheight?: string;
  gap?: string;
  $change?: boolean;
  text?: string;
  fontsize?: string;
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