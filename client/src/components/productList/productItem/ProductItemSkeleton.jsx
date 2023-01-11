import { ProductItemSkeletonArea } from './ProductItemSkeleton.style';

const ProductItemSkeleton = () => {
  return (
    <>
      <ProductItemSkeletonArea>
        <div className="imageArea" />
        <div className="productInfo">
          <div className="flexArea">
            <div className="productTitle" />
            <div className="productLocation" />
          </div>
          <div className="flexArea">
            <div className="productPrice" />
          </div>
        </div>
      </ProductItemSkeletonArea>
    </>
  );
};

export default ProductItemSkeleton;
