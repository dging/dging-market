import { useSell } from '../recoil/sell/useSell';

export const onPressEnter = (
  e: React.KeyboardEvent<HTMLDivElement>,
  props: {
    action: any;
    value?: any;
  }
) => {
  e.stopPropagation();
  //   e.preventDefault();
  console.log(e.nativeEvent.isComposing);

  if (
    e.code === 'Enter' &&
    e.shiftKey === false &&
    e.nativeEvent.isComposing === false
  ) {
    console.log('submit');
    props.action?.();
  } else if (e.key === 'Enter' && e.shiftKey === true) {
    console.log(e.shiftKey);
    console.log('new line');
  } else if (e.key === 'Esc') {
    console.log('ese, cancel');
  } else {
    console.log('else');
  }
};
