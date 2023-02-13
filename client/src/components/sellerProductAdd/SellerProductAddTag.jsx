import { SellerProductAddTagArea, TagsInput } from './SellerProductAddTag.style';

const SellerProductAddTag = ({ tags, addTags, removeTags, tagContent, setTagContent }) => {
  return (
    <>
      <SellerProductAddTagArea>
        <h2>주변 편의시설</h2>
        <TagsInput>
          <ul id="tags">
            {tags.map((tag, index) => (
              <li key={index} className="tag">
                <span className="tag-title">{tag}</span>
                <span role="presentation" className="tag-close-icon" onClick={() => removeTags(index)}>
                  X
                </span>
              </li>
            ))}
          </ul>
          <input
            className="tag-input"
            type="text"
            value={tagContent}
            onChange={(e) => setTagContent(e.target.value)}
            onKeyUp={(event) => {
              event.key === 'Enter' && addTags(event);
            }}
            placeholder="Press enter to add tags"
          />
        </TagsInput>
      </SellerProductAddTagArea>
    </>
  );
};

export default SellerProductAddTag;
