/*
 * GRAKN.AI - THE KNOWLEDGE GRAPH
 * Copyright (C) 2018 Grakn Labs Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package grakn.core.graql.query;

import grakn.core.graql.query.pattern.statement.Statement;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * A query for undefining the Schema types.
 * The query will undefine all concepts described in the pattern provided.
 */
public class UndefineQuery implements Query {

    private final List<? extends Statement> statements;

    UndefineQuery(List<? extends Statement> statements) {
        if (statements == null) {
            throw new NullPointerException("Null statements");
        } else if (statements.isEmpty()) {
            throw new IllegalArgumentException("Define Statements could not be empty");
        }
        this.statements = statements;
    }

    public List<? extends Statement> statements() {
        return statements;
    }

    @Override @SuppressWarnings("Duplicates")
    public String toString() {
        StringBuilder query = new StringBuilder();

        query.append(Command.UNDEFINE);
        if (statements.size()>1) query.append(Char.NEW_LINE);
        else query.append(Char.SPACE);

        query.append(statements().stream()
                             .map(Statement::toString)
                             .collect(joining(Char.NEW_LINE.toString())));

        return query.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UndefineQuery that = (UndefineQuery) o;

        return this.statements.equals(that.statements);
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.statements.hashCode();
        return h;
    }
}
