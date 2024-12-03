export const onPressEnter = <T>(
  e: React.KeyboardEvent<HTMLDivElement>,
  props: {
    action?: T;
    value?: string | number;
  }
) => {
  if (e.key === 'Enter' && e.shiftKey === false) {
    console.log('submit');
  } else if (e.key === 'Enter' && e.shiftKey === true) {
    console.log(e.shiftKey);
    console.log('new line');
  } else if (e.key === 'Esc') {
    console.log('ese, cancel');
  }
};
