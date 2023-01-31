import styled from 'styled-components';

export const FacilityModalArea = styled.div`
  width: 100%;
  height: 90%;
  overflow-y: scroll;
  flex-direction: column;
  padding-right: 25px;
  h2 {
    font-size: 24px;
    font-weight: 700;
    margin-bottom: 30px;
  }
  .facilityList {
    width: 100%;
    display: flex;
    flex-direction: column;
    .facilityItem {
      width: 100%;
      padding: 25px 0px;
      border-bottom: 1px solid ${(props) => props.theme.gray};
      font-weight: 300;
      font-size: 19px;
    }
    .facilityItem:last-child {
      border: none;
    }
  }
`;
