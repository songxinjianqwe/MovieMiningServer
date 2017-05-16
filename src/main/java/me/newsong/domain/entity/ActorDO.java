package me.newsong.domain.entity;

public class ActorDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column actor.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column actor.actor_name
     *
     * @mbggenerated
     */
    private String actorName;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbggenerated
     */
    public ActorDO(Long id, String actorName) {
        this.id = id;
        this.actorName = actorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbggenerated
     */
    public ActorDO() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column actor.id
     *
     * @return the value of actor.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column actor.id
     *
     * @param id the value for actor.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column actor.actor_name
     *
     * @return the value of actor.actor_name
     *
     * @mbggenerated
     */
    public String getActorName() {
        return actorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column actor.actor_name
     *
     * @param actorName the value for actor.actor_name
     *
     * @mbggenerated
     */
    public void setActorName(String actorName) {
        this.actorName = actorName == null ? null : actorName.trim();
    }

    @Override
    public String toString() {
        return "ActorDO{" +
                "id=" + id +
                ", actorName='" + actorName + '\'' +
                '}';
    }
}