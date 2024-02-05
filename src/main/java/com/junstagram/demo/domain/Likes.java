package com.junstagram.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table (
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "post_user",
                        columnNames = {"post_id" , "user_id"}
                )
        }
)
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // FetchType.LAZY는 연관된 엔티티를 실제로 사용할 때까지 데이터베이스에서 로딩하지 않고, 해당 엔티티가 실제로 필요한 시점에 로딩
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"posts"})
    private User user;

    @Builder // @Builder 어노테이션을 사용하면 개발자는 해당 클래스에 대한 빌더(builder) 메서드를 자동으로 생성할 수 있으며, 이를 통해 객체를 초기화할 때 매개변수를 지정할 수 있다.
    public Likes(Post post , User user) {
        this.post = post;
        this.user = user;
    }

}
