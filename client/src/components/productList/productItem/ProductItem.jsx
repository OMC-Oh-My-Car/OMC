import { useState } from 'react';
import { ProductItemArea } from './ProductItem.style';
import { faStar, faCircleRight, faCircleLeft } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { ReactComponent as HeartRegular } from '../../../assets/images/heart-regular.svg';
import { ReactComponent as HeartSolid } from '../../../assets/images/heart-solid.svg';

const ProductItem = ({ handleClick, item }) => {
  const [currentImage, setCurrentImage] = useState(0);
  const [hover, setHover] = useState(false);

  return (
    <>
      <ProductItemArea
        onMouseOver={() => setHover(true)}
        onFocus={() => setHover(true)}
        onMouseOut={() => setHover(false)}
        onBlur={() => setHover(false)}
      >
        <div className="imageArea">
          {item.isLike ? (
            <>
              <HeartSolid fill="red" className="heartIcon" alt="heartIcon" />
            </>
          ) : (
            <>
              <HeartRegular fill="red" className="heartIcon" alt="heartIcon" />
            </>
          )}

          <img role="presentation" src={item.img[currentImage]} alt="상품사진" onClick={() => handleClick(item.id)} />
          {currentImage !== 0 && (
            <>
              <FontAwesomeIcon
                onClick={() => setCurrentImage(currentImage - 1)}
                className={!hover ? 'circleLeftIcon disabled' : 'circleLeftIcon'}
                icon={faCircleLeft}
              />
            </>
          )}

          {currentImage !== item.img.length - 1 && (
            <>
              <FontAwesomeIcon
                onClick={() => setCurrentImage(currentImage + 1)}
                className={!hover ? 'circleRightIcon disabled' : 'circleRightIcon'}
                icon={faCircleRight}
              />
            </>
          )}
        </div>
        <div className="productInfo" onClick={() => handleClick(item.productId)} role="presentation">
          <div className="flexArea">
            <p className="productTitle">{item.subject}</p>
            <p className="productLocation">{item.address}</p>
          </div>
          <div className="flexArea">
            <p className="productPrice">
              ₩{item.price}
              <span className="colorGray"> /박</span>
            </p>
          </div>
          <p className="productGrade">
            <FontAwesomeIcon className="starIcon" icon={faStar} />
            <span>{item.star}</span>
          </p>
        </div>
      </ProductItemArea>
    </>
  );
};

export default ProductItem;
