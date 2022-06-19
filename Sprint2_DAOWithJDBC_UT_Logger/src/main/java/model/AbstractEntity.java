package model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Standard string rule:
 *      the limit of characters: up to the objects
 *      No blanks/spaces at the beginning and at the end of the string.
 *      Each word is separated by a space.
 * Accepted input: "THis iS    noT 1 STANDARD       strING".
 * return: "this is not 1 standard string".
 */

/**
 * All classes have to "extends" from AbstractEntity.
 * Contain 4 common fields: id, version, createdAt, modifiedAt.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class AbstractEntity <T extends AbstractEntity<T>> implements Comparable<T>{
    /**
     * id: not null or blank, unique, distinct.
     * @java.utils.UUID ?
     * Usage: to identify uniquely each entity instance.
     */
    protected long id;
    /**
     * version: not null or blank, start from 0.
     * usage: keep track of how many times an entity was modified.
     */
    protected int version;
    /**
     * createdAt: not null or blank, LocalDateTime.now() to make.
     * usage: audit each entity instance by created time.
     */
    protected LocalDateTime createdAt;
    /**
     * modifiedAT: not null or blank, LocalDateTime.now() to make.
     * usage: audit each entity instance by modified time.
     */
    protected LocalDateTime modifiedAt;
}
