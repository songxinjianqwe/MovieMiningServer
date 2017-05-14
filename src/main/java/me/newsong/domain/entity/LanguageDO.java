package me.newsong.domain.entity;

public class LanguageDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column language.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column language.language_name
     *
     * @mbggenerated
     */
    private String languageName;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table language
     *
     * @mbggenerated
     */
    public LanguageDO(Long id, String languageName) {
        this.id = id;
        this.languageName = languageName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table language
     *
     * @mbggenerated
     */
    public LanguageDO() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column language.id
     *
     * @return the value of language.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column language.id
     *
     * @param id the value for language.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column language.language_name
     *
     * @return the value of language.language_name
     *
     * @mbggenerated
     */
    public String getLanguageName() {
        return languageName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column language.language_name
     *
     * @param languageName the value for language.language_name
     *
     * @mbggenerated
     */
    public void setLanguageName(String languageName) {
        this.languageName = languageName == null ? null : languageName.trim();
    }

    @Override
    public String toString() {
        return "LanguageDO{" +
                "id=" + id +
                ", languageName='" + languageName + '\'' +
                '}';
    }
}