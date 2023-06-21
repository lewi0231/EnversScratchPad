package lewis.enverstesting;

import jakarta.persistence.*;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity(name = "revisions")
@RevisionEntity
public class CustomRevisionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revision_seq")
    @SequenceGenerator(name = "revision_seq", sequenceName = "revisions_seq", allocationSize = 1)
    @RevisionNumber
    private int revision;

    @RevisionTimestamp
    private long timestamp;

    // Your additional custom fields
}