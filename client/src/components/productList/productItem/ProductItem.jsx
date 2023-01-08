import { useState } from 'react';
import { ProductItemArea } from './ProductItem.style';
import { faStar, faCircleRight, faCircleLeft } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { ReactComponent as HeartRegular } from '../../../assets/images/heart-regular.svg';
import { ReactComponent as HeartSolid } from '../../../assets/images/heart-solid.svg';

const ProductItem = ({ item }) => {
  const [currentImage, setCurrentImage] = useState(0);

  return (
    <>
      <ProductItemArea>
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
          <img src={item.img[currentImage].value} alt="" />
          {currentImage !== 0 && (
            <>
              <FontAwesomeIcon
                onClick={() => setCurrentImage(currentImage - 1)}
                className="circleLeftIcon"
                icon={faCircleLeft}
              />
            </>
          )}
          {currentImage !== item.img.length - 1 && (
            <>
              <FontAwesomeIcon
                onClick={() => setCurrentImage(currentImage + 1)}
                className="circleRightIcon"
                icon={faCircleRight}
              />
            </>
          )}
        </div>
        <div className="productInfo">
          <div className="flexArea">
            <p className="productTitle">{item.subject}</p>
            <p className="productLocation">{item.location[0].value}</p>
          </div>
          <div className="flexArea">
            <p className="productPrice">
              ₩{item.price}
              <span className="colorGray"> /박</span>
            </p>
          </div>
          <p className="productIsLike">
            <FontAwesomeIcon className="starIcon" icon={faStar} />
            <span>{item.star}</span>
          </p>
        </div>
      </ProductItemArea>
    </>
  );
};

export default ProductItem;
