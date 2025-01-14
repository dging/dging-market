export const addComma = (num: string | number) => {
  if (typeof num === 'number') {
    return num.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',');
  } else if (typeof num === 'string') {
    return num.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',');
  } else {
    return 0;
  }
};
