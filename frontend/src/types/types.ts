export interface BtnType {
  as?: string;
  margin?: string;
  children?: React.ReactNode;
  width?: string;
  height?: string;
  $status?: boolean;
  $backgroundimage?: string;
  $backgroundposition?: string;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

export interface BtnDivType {
  as?: string;
  margin?: string;
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
  position?: string;
  display?: string;
  width?: any;
  height?: any;
  margin?: string;
  padding?: any;
  flexdirection?: string;
  justifycontent?: string;
  alignitems?: string;
  gap?: string;
  textalign?: string;
  children?: React.ReactNode;
  $top?: boolean;
  $bottom?: boolean;
  $backgroundimage?: any;
  $backgroundposition?: string;
}
