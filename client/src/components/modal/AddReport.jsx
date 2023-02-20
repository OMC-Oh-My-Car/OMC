import { useState } from 'react';
import { AddReportArea } from './AddReport.style';
import { useMutation } from 'react-query';
import { useLocation } from 'react-router-dom';
import { addProductReport } from '../../modules/productReport/addProductReport';

const AddReport = () => {
  const location = useLocation();
  const productId = location.pathname.split('/')[2];

  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  // 컨텐츠 변경 함수
  const contentControl = (e) => {
    setContent(e.target.value);
  };
  // 예약 취소 함수
  const mutation = useMutation(() => addProductReport({ subject: title, content }, productId), {
    onMutate() {},
    onSuccess(data) {
      console.log(data);
    },
    onError(err) {
      console.log(err);
    },
  });

  return (
    <>
      <AddReportArea>
        <h2>상품 신고하기</h2>
        <span className="explain">이러한 사유로 상품을 신고합니다.</span>

        <span className="title">신고 제목</span>
        <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />

        <span className="content">신고 내용</span>
        <textarea value={content} onChange={contentControl} type="text" rows="10" cols="15" />

        <button onClick={() => mutation.mutate()} className="more">
          제출하기
        </button>
      </AddReportArea>
    </>
  );
};

export default AddReport;
