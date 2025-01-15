// 전체 포함된 카테고리 데이터

export const words = ['전체', '판매중', '예약중', '판매완료'];
export const items1 = ['전체', 'CD', '바이닐', '카세트', 'DVD'];
export const items2 = [
  '전체',
  'POP',
  'K-POP',
  'J-POP',
  'Rock',
  '월드 뮤직',
  '발라드',
  'RAP/힙합',
  '알앤비/소울',
  '인디',
  '일렉트로닉/하우스',
  '뉴에이지',
  '재즈/블루스',
  '클래식',
  '종교/명상',
  '컴필레이션',
  '포크',
  '기타',
];

export const items3 = [
  '전체',
  'Rock',
  'British / Modern / Garage / Indie',
  'Metal / Hardcore',
  'Alternative / Grunge',
  'Hard Rock',
  'Punk',
  'Art / Progressive / Psychedelic',
  'Old Rock',
  'Rock 컴필레이션',
];

// 전체 없는 카테고리 데이터

export const category1 = ['CD', '바이닐', '카세트', 'DVD'];
export const category2 = [
  'POP',
  'K-POP',
  'J-POP',
  'Rock',
  '월드 뮤직',
  '발라드',
  'RAP/힙합',
  '알앤비/소울',
  '인디',
  '일렉트로닉/하우스',
  '뉴에이지',
  '재즈/블루스',
  '클래식',
  '종교/명상',
  '컴필레이션',
  '포크',
  '기타',
];

export const category3 = [
  'Rock',
  'British / Modern / Garage / Indie',
  'Metal / Hardcore',
  'Alternative / Grunge',
  'Hard Rock',
  'Punk',
  'Art / Progressive / Psychedelic',
  'Old Rock',
  'Rock 컴필레이션',
];

// 거래톡 미리보기 데이터

export const profile_cheat = [
  {
    id: 0,
    name: 'FDOPE1',
    date: '9월 7일',

    content:
      'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus a justo nec ipsum cursus sagittis nec ac enim. Sed aliquam.',
  },
  {
    id: 1,
    name: 'FDOPE2',
    date: '9월 8일',
    content:
      'Vivamus a justo nec ipsum cursus sagittis nec ac enim. Sed aliquam.',
  },
  {
    id: 2,
    name: 'FDOPE3',
    date: '9월 9일',
    content: 'hello',
  },
];

// 판매내역 데이터

export const sellData = [
  {
    id: 0,
    title: 'TEST - TEST1',
    price: '10000',
    paydate: '2024-10-14 22:49',
    buyer: '닉네임1',
    state: '취소 / 환불',
    tradeway: 'safe',
    review: false,
  },
  {
    id: 1,
    title: 'TEST - TEST2',
    price: '20000',
    paydate: '2024-10-15 22:49',
    buyer: '닉네임2',
    state: '결제완료',
    tradeway: 'safe',
    review: true,
  },
  {
    id: 2,
    title: 'TEST - TEST3',
    price: '30000',
    paydate: '2024-10-15 10:23',
    buyer: '닉네임3',
    state: '결제완료',
    tradeway: 'common',
    review: false,
  },
  {
    id: 3,
    title: 'TEST - TEST4',
    price: '40000',
    paydate: '2024-12-01 04:56',
    buyer: '닉네임4',
    state: '취소 / 환불',
    tradeway: 'common',
    review: false,
  },
  {
    id: 4,
    title: 'TEST - TEST5',
    price: '50000',
    paydate: '2024-11-24 14:07',
    buyer: '닉네임5',
    state: '결제완료',
    tradeway: 'safe',
    review: true,
  },
  {
    id: 5,
    title: 'TEST - TEST6',
    price: '60000',
    paydate: '2024-12-14 02:49',
    buyer: '닉네임6',
    state: '결제완료',
    tradeway: 'common',
    review: true,
  },
  {
    id: 6,
    title: 'TEST - TEST7',
    price: '70000',
    paydate: '2024-11-13 20:10',
    buyer: '닉네임7',
    state: '결제완료',
    tradeway: 'safe',
    review: false,
  },
];

// 구매내역 데이터

export const BuyData = [
  {
    id: 0,
    title: 'TEST - TEST1',
    price: '10000',
    buydate: '24년 02월 17일',
    trade: '택배거래',
    seller: '닉네임1',
    state: '취소 / 환불',
    tradeway: 'safe',
    review: false,
  },
  {
    id: 1,
    title: 'TEST - TEST2',
    price: '20000',
    buydate: '23년 01월 10일',
    trade: '택배거래',
    seller: '닉네임2',
    state: '결제완료',
    tradeway: 'common',
    review: true,
  },
  {
    id: 2,
    title: 'TEST - TEST3',
    price: '30000',
    buydate: '24년 05월 23일',
    trade: '택배거래',
    seller: '닉네임3',
    state: '결제완료',
    tradeway: 'safe',
    review: false,
  },
  {
    id: 3,
    title: 'TEST - TEST4',
    price: '40000',
    buydate: '24년 09월 23일',
    trade: '택배거래',
    seller: '닉네임4',
    state: '취소 / 환불',
    tradeway: 'safe',
    review: false,
  },
  {
    id: 4,
    title: 'TEST - TEST5',
    price: '50000',
    buydate: '24년 03월 01일',
    trade: '택배거래',
    seller: '닉네임5',
    state: '결제완료',
    tradeway: 'common',
    review: true,
  },
  {
    id: 5,
    title: 'TEST - TEST6',
    price: '60000',
    buydate: '24년 05월 05일',
    trade: '택배거래',
    seller: '닉네임6',
    state: '예약중',
    tradeway: 'common',
    review: true,
  },
  {
    id: 6,
    title: 'TEST - TEST7',
    price: '70000',
    buydate: '24년 04월 27일',
    trade: '택배거래',
    seller: '닉네임7',
    state: '판매완료',
    tradeway: 'safe',
    review: false,
  },
];

// 거래톡 채팅 데이터

export const cheat = [
  {
    id: 0,
    title: 'TEST1 - TEST1',
    name: 'FDOPE1',
    price: 200000,
    date: '9월 7일',
    last_connect: '13일 전',
    state: '무료배송',
    talk: [
      { name: 'FDOPE1', content: 'hello' },
      { name: 'you', content: 'hello' },
      {
        name: 'FDOPE1',
        content:
          'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus a justo nec ipsum cursus sagittis nec ac enim. Sed aliquam.',
      },
      {
        name: 'FDOPE1',
        content:
          'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque imperdiet risus quis tellus maximus ullamcorper. Cras condimentum non nisl eget.',
      },
      { name: 'you', content: 'hello' },
      { name: 'you', content: 'hello' },
      { name: 'FDOPE1', content: 'hello' },
      { name: 'you', content: 'hello' },
    ],
  },

  {
    id: 1,
    title: 'TEST2 - TEST2',
    name: 'FDOPE2',
    price: 300000,
    date: '9월 8일',
    last_connect: '13일 전',
    state: '무료배송',
    talk: [
      { name: 'FDOPE2', content: 'hello' },
      { name: 'you', content: 'hello' },
      {
        name: 'FDOPE2',
        content:
          'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus a justo nec ipsum cursus sagittis nec ac enim. Sed aliquam.',
      },
      {
        name: 'FDOPE2',
        content:
          'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque imperdiet risus quis tellus maximus ullamcorper. Cras condimentum non nisl eget.',
      },
      { name: 'you', content: 'hello' },
      { name: 'you', content: 'hello' },
      { name: 'FDOPE2', content: 'hello' },
      { name: 'you', content: 'hello' },
    ],
  },

  {
    id: 2,
    title: 'TEST3 - TEST3',
    name: 'FDOPE3',
    price: 400000,
    date: '9월 9일',
    state: '유료배송',
    talk: [
      { name: 'FDOPE3', content: 'hello' },
      { name: 'you', content: 'hello' },
      {
        name: 'FDOPE3',
        content:
          'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus a justo nec ipsum cursus sagittis nec ac enim. Sed aliquam.',
      },
      {
        name: 'FDOPE3',
        content:
          'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque imperdiet risus quis tellus maximus ullamcorper. Cras condimentum non nisl eget.',
      },
      { name: 'you', content: 'hello' },
      { name: 'you', content: 'hello' },
      { name: 'FDOPE3', content: 'hello' },
      { name: 'you', content: 'hello' },
    ],
  },
];
