import { styled, useTheme } from 'styled-components';
import ImgBtn from '../Button/ImgBtn';
import Arrange from '../Base/Arrange';
import DiskGray from '../../assets/images/DiskGray.png';
import Search from '../../assets/images/Search.png';

const WrapSearchbar = styled(Arrange)`
  border: ${(props) =>
    props.$status ? 'none' : `1px solid ${props.theme.color.black1}`};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  background-color: ${(props) =>
    props.$status ? props.theme.color.black3 : 'white'};
`;

const SearchInput = styled.input<{ $status?: boolean }>`
  width: 100%;
  height: 24px;
  padding: 2px 0 0 10px;
  background-color: transparent;
  border: none;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.body16};

  &:focus {
    outline: none;
  }

  ::placeholder,
  ::-webkit-input-placeholder {
    color: ${({ theme }) => theme.color.black2};
  }
  ::-ms-input-placeholder {
    color: ${({ theme }) => theme.color.black2};
  }
`;

export default function Searchbar(props: {
  $type?: boolean;
  onChange?: React.ChangeEventHandler<HTMLInputElement>;
}) {
  const theme = useTheme();

  return (
    <WrapSearchbar
      width={props.$type ? '637px' : '570px'}
      height='46px'
      padding={theme.size.xxs}
      display='flex'
      alignitems='center'
      $status={props.$type}
      {...props}
    >
      <ImgBtn as='div' $backgroundimage={props.$type ? DiskGray : Search} />
      <SearchInput
        placeholder={props.$type ? '상품명 / 상점명 검색' : '상품명 검색'}
        onChange={props.onChange}
        $status={props.$type}
      />
    </WrapSearchbar>
  );
}
