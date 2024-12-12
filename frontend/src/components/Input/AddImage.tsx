import React, { useState } from 'react';
import styled from 'styled-components';
import { useSell } from '../../recoil/sell/useSell';
import { ImageRegistration } from '../../assets/images';

const WrapInput = styled.div`
  div {
    width: 240px;
    height: 240px;
    border: 1px solid ${({ theme }) => theme.color.black1};
    border-radius: 4px;
    background: ${({ theme }) =>
      `${theme.color.black3} url(${ImageRegistration}) no-repeat center / 100%`};
    overflow: hidden;
    cursor: pointer;
  }

  label {
    display: inline-block;
    font-size: inherit;
    line-height: normal;
    vertical-align: middle;
    cursor: pointer;
  }

  input[type='file'] {
    position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    margin: -1px;
    overflow: hidden;
    clip: rect(0, 0, 0, 0);
    border: 0;
  }
`;

interface FileInfo {
  name: string;
  size: number;
  type: string;
}

export default function AddImage() {
  const [files, setFiles] = useState<FileInfo[]>([]);
  const { sellImage, setSellImage, addImage } = useSell();

  return (
    <>
      <WrapInput>
        <label htmlFor='file'>
          <div></div>
        </label>
        <input
          id='file'
          type='file'
          name='file'
          accept='image/*'
          multiple
          onChange={(e) => {
            if (e.target.files?.[0]) {
              addImage(e.target.files[0]);
            }
          }}
        />
      </WrapInput>
      <div style={{ display: 'flex', flexWrap: 'wrap', marginTop: '20px' }}>
        {sellImage.map((val, idx) => (
          <img
            key={idx}
            src={val}
            alt={`Preview ${idx}`}
            style={{
              width: '100px',
              height: '100px',
              objectFit: 'cover',
              borderRadius: '8px',
              margin: '10px',
            }}
          />
        ))}
      </div>
    </>
  );
}
