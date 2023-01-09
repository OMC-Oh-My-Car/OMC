// import React from 'react';
import { ProductListArea } from './ProductList.style';
import ProductItem from './productItem/ProductItem';

const ProductList = ({ filter, setFilter }) => {
  // 임시 데이터
  let data = [
    {
      productId: 1,
      subject: '전주 한옥마을',
      description: '상품 설명',
      location: [{ value: '전라북도 전주시' }, { value: '마포구' }, { value: '월드컵로' }],
      reportCount: 14,
      price: 100000,
      star: 3.52,
      img: [
        {
          value:
            'https://www.hyundai.co.kr/image/upload/asset_library/MDA00000000000005326/9c99a0fb7e254d338ba6d92c7eb5d874.jpg',
        },
        { value: 'https://dimg.donga.com/ugc/CDB/WOMAN/Article/5e/fb/e2/46/5efbe2462079d2738de6.jpg' },
      ],
      like: 10,
      isLike: true,
    },
    {
      productId: 2,
      subject: '상호명',
      description: '상품 설명',
      location: [{ value: '서울특별시' }, { value: '마포구' }, { value: '월드컵로' }],
      reportCount: 14,
      price: 100000,
      star: 3.52,
      img: [
        {
          value:
            'https://www.hyundai.co.kr/image/upload/asset_library/MDA00000000000005326/9c99a0fb7e254d338ba6d92c7eb5d874.jpg',
        },
        { value: 'https://dimg.donga.com/ugc/CDB/WOMAN/Article/5e/fb/e2/46/5efbe2462079d2738de6.jpg' },
      ],
      like: 10,
      isLike: false,
    },
  ];

  return (
    <>
      <ProductListArea>
        <div className="productFilter">
          <button className={`productFilterItem ${filter === 0 ? ' active' : ''}`} onClick={() => setFilter(0)}>
            최신순
          </button>
          <button className={`productFilterItem ${filter === 1 ? ' active' : ''}`} onClick={() => setFilter(1)}>
            인기순
          </button>
          <button className={`productFilterItem ${filter === 2 ? ' active' : ''}`} onClick={() => setFilter(2)}>
            조회순
          </button>
          <button className={`productFilterItem ${filter === 3 ? ' active' : ''}`} onClick={() => setFilter(3)}>
            추천순
          </button>
        </div>
        <div className="productList">
          {data.map((item) => {
            return <ProductItem key={item.productId} item={item} />;
          })}
        </div>
      </ProductListArea>
    </>
  );
};

export default ProductList;
