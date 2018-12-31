/*
 * Copyright 2017-2019 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.micronaut.graphql;

import graphql.GraphQL;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.util.StringUtils;

import javax.annotation.Nullable;
import javax.inject.Singleton;

/**
 * The GraphQL factory.
 *
 * @author Marcel Overdijk
 * @since 1.0
 */
@Factory
@Requires(property = GraphQLConfiguration.ENABLED, value = StringUtils.TRUE, defaultValue = StringUtils.TRUE)
@Requires(beans = GraphQL.class)
public class GraphQLFactory {

    /**
     * Creates the {@link GraphQLExecutionResultHandler} bean.
     *
     * @return the GraphQL execution result handler bean
     */
    @Bean
    @Singleton
    public GraphQLExecutionResultHandler graphQLExecutionResultHandler() {
        return new DefaultGraphQLExecutionResultHandler();
    }

    /**
     * Creates the {@link GraphQLInvocation} bean.
     *
     * @param graphQL               the {@link GraphQL} instance
     * @param graphQLContextBuilder the {@link GraphQLContextBuilder} instance
     * @param graphQLRootBuilder    the {@link GraphQLRootBuilder} instance
     * @return the GraphQL invocation bean
     */
    @Bean
    @Singleton
    public GraphQLInvocation graphQLInvocation(GraphQL graphQL, @Nullable GraphQLContextBuilder graphQLContextBuilder,
            @Nullable GraphQLRootBuilder graphQLRootBuilder) {
        return new DefaultGraphQLInvocation(graphQL, graphQLContextBuilder, graphQLRootBuilder);
    }
}