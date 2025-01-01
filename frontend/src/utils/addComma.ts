export const addComma = (num: string) => {
  return num.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',');
};
