package me.newsong.domain.entity;

public class WriterDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column writer.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column writer.writer_name
     *
     * @mbggenerated
     */
    private String writerName;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table writer
     *
     * @mbggenerated
     */
    public WriterDO(Long id, String writerName) {
        this.id = id;
        this.writerName = writerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table writer
     *
     * @mbggenerated
     */
    public WriterDO() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column writer.id
     *
     * @return the value of writer.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column writer.id
     *
     * @param id the value for writer.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column writer.writer_name
     *
     * @return the value of writer.writer_name
     *
     * @mbggenerated
     */
    public String getWriterName() {
        return writerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column writer.writer_name
     *
     * @param writerName the value for writer.writer_name
     *
     * @mbggenerated
     */
    public void setWriterName(String writerName) {
        this.writerName = writerName == null ? null : writerName.trim();
    }

    @Override
    public String toString() {
        return "WriterDO{" +
                "id=" + id +
                ", writerName='" + writerName + '\'' +
                '}';
    }
}