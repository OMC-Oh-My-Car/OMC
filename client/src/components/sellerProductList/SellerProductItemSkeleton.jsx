import { SellerProductItemSkeletonArea } from './SellerProductItemSkeleton.style';

const SellerProductItemSkeleton = () => {
  return (
    <>
      <SellerProductItemSkeletonArea>
        <div className="imageArea" />
        <div className="productInfo">
          <span className="productTitle" />
          <div className="button buttonRed" />
          <div className="button buttonYellow" />
        </div>
      </SellerProductItemSkeletonArea>
    </>
  );
};

export default SellerProductItemSkeleton;
