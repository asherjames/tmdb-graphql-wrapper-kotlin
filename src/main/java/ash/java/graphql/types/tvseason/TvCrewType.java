package ash.java.graphql.types.tvseason;

import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;

@GraphQLName("TvCrew")
public class TvCrewType extends TvPersonType {

    @GraphQLField
    private String department;

    @GraphQLField
    private String job;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
