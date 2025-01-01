import { Dispatch, SetStateAction } from 'react';

export const setState = (
  newValue: any,
  updater: Dispatch<SetStateAction<any>>
) => {
  updater(newValue);
};
