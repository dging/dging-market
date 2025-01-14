import dayjs from 'dayjs';

export const calcTime = (time: string) => {
  const now = dayjs(new Date());
  const createTime = dayjs(time);

  const diff = {
    years: now.diff(createTime, 'years'),
    months: now.diff(createTime, 'months'),
    days: now.diff(createTime, 'days'),
    hours: now.diff(createTime, 'hours'),
    minutes: now.diff(createTime, 'minutes'),
    seconds: now.diff(createTime, 'seconds'),
  };

  if (diff.years > 0) {
    return `${diff.years}년 전`;
  } else if (diff.months > 0) {
    return `${diff.months}달 전`;
  } else if (diff.days > 0) {
    return `${diff.days}일 전`;
  } else if (diff.hours > 0) {
    return `${diff.hours}시간 전`;
  } else if (diff.minutes > 0) {
    return `${diff.minutes}분 전`;
  } else if (diff.seconds > 1) {
    return `${diff.seconds}초 전`;
  } else {
    return '방금전';
  }
};
