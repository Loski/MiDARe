package examples;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface UserMixin {

	@JsonIgnore
    abstract int getUsername();
}
