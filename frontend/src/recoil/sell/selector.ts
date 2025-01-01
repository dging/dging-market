import { selector } from 'recoil';
import { SellState, SellTag, SellName } from './atom';

export const SellNameUpdateState = selector({
  key: 'SellNameUpdate',
  get: ({ get }) => {
    console.log('name');
  },
});

// 태그 갯수 확인
export const SellTagCountState = selector({
  key: 'SellTagCount',
  get: ({ get }) => {
    console.log('test');
  },
  set: ({ get, set }, tag) => {
    const sellTag = get(SellTag);
    console.log(sellTag);

    // if (sellTag.length > 5) {
    //   return 'full';
    // } else {
    //   console.log(tag);
    //   return tag;
    // }
  },
});
