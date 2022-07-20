@file:Suppress("unused")

package pw.avi.arangodbdriver

import com.arangodb.ArangoCursor
import com.arangodb.async.ArangoDatabaseAsync
import com.arangodb.entity.*
import com.arangodb.entity.arangosearch.analyzer.SearchAnalyzer
import com.arangodb.model.*
import com.arangodb.model.arangosearch.AnalyzerDeleteOptions
import com.arangodb.model.arangosearch.ArangoSearchCreateOptions
import kotlinx.coroutines.future.await

// ==== createCollection ====

suspend inline fun ArangoDatabaseAsync.createCollectionAsync(
    name: String,
    optionsBuilder: (CollectionCreateOptions.() -> Unit) = {}
): CollectionEntity =
    createCollection(name, CollectionCreateOptions().apply(optionsBuilder)).await()

// ==== getCollections ====

suspend inline fun ArangoDatabaseAsync.getCollectionsAsync(optionsBuilder: (CollectionsReadOptions.() -> Unit) = {}): MutableCollection<CollectionEntity> =
    getCollections(CollectionsReadOptions().apply(optionsBuilder)).await()

// ==== cursor ====

suspend inline fun <reified T> ArangoDatabaseAsync.cursorAsync(cursorId: String): ArangoCursor<T> = cursor(cursorId, T::class.java).await()

// ==== aql function ====

suspend inline fun ArangoDatabaseAsync.createAqlFunctionAsync(
    name: String,
    code: String,
    optionsBuilder: (AqlFunctionCreateOptions.() -> Unit) = {}
) {
    createAqlFunction(name, code, AqlFunctionCreateOptions().apply(optionsBuilder)).await()
}

suspend inline fun ArangoDatabaseAsync.deleteAqlFunctionAsync(
    name: String,
    optionsBuilder: (AqlFunctionDeleteOptions.() -> Unit) = {}
): Int = deleteAqlFunction(name, AqlFunctionDeleteOptions().apply(optionsBuilder)).await()

suspend inline fun ArangoDatabaseAsync.getAqlFunctionsAsync(optionsBuilder: (AqlFunctionGetOptions.() -> Unit) = {}): Collection<AqlFunctionEntity> =
    getAqlFunctions(AqlFunctionGetOptions().apply(optionsBuilder)).await()

// ==== graphs ====

suspend inline fun ArangoDatabaseAsync.createGraphAsync(
    name: String,
    edgeDefinitions: Collection<EdgeDefinition>,
    optionsBuilder: (GraphCreateOptions.() -> Unit) = {}
): GraphEntity = createGraph(name, edgeDefinitions, GraphCreateOptions().apply(optionsBuilder)).await()

// ==== transactions ====

suspend inline fun <reified T> ArangoDatabaseAsync.transactionAsync(
    action: String,
    optionsBuilder: (TransactionOptions.() -> Unit) = {}
): T = transaction(action, T::class.java, TransactionOptions().apply(optionsBuilder)).await()

suspend inline fun ArangoDatabaseAsync.beginStreamTransactionAsync(optionsBuilder: (StreamTransactionOptions.() -> Unit) = {}): StreamTransactionEntity =
    beginStreamTransaction(StreamTransactionOptions().apply(optionsBuilder)).await()

// ==== get document ====

suspend inline fun <reified T> ArangoDatabaseAsync.getDocumentAsync(
    id: String,
    optionsBuilder: (DocumentReadOptions.() -> Unit) = {}
): T = getDocument(id, T::class.java, DocumentReadOptions().apply(optionsBuilder)).await()

// ==== createArangoSearch ====

suspend inline fun ArangoDatabaseAsync.createArangoSearchAsync(
    name: String,
    optionsBuilder: (ArangoSearchCreateOptions.() -> Unit) = {}
): ViewEntity = createArangoSearch(name, ArangoSearchCreateOptions().apply(optionsBuilder)).await()

// ==== analyzers ====

suspend inline fun ArangoDatabaseAsync.createSearchAnalyzerAsync(builder: (() -> SearchAnalyzer)): SearchAnalyzer =
    createSearchAnalyzer(builder()).await()

suspend fun ArangoDatabaseAsync.getSearchAnalyzerAsync(name: String): SearchAnalyzer = getSearchAnalyzer(name).await()

suspend fun ArangoDatabaseAsync.getSearchAnalyzersAsync(): Collection<SearchAnalyzer> = searchAnalyzers.await()

suspend inline fun ArangoDatabaseAsync.deleteSearchAnalyzerAsync(
    name: String,
    optionsBuilder: (AnalyzerDeleteOptions.() -> Unit) = {}
) {
    deleteSearchAnalyzer(name, AnalyzerDeleteOptions().apply(optionsBuilder)).await()
}

// ==== queries ====

suspend inline fun <reified T> ArangoDatabaseAsync.queryAsync(
    aql: String,
    builder: (AqlQueryBuilder.() -> Unit) = {}
): ArangoCursor<T> = with(AqlQueryBuilder()) {
    builder()
    query(aql, bindVars.ifEmpty { null }, options, T::class.java).await()
}

// ==== explainQuery ====

suspend inline fun ArangoDatabaseAsync.explainQueryAsync(
    aql: String,
    builder: (AqlQueryExplainBuilder.() -> Unit) = {}
): AqlExecutionExplainEntity = with(AqlQueryExplainBuilder()) {
    builder()
    explainQuery(aql, bindVars.ifEmpty { null }, options).await()
}

// ==== other functions returning future ====

suspend fun ArangoDatabaseAsync.getVersionAsync(): ArangoDBVersion? = version.await()

suspend fun ArangoDatabaseAsync.getEngineAsync(): ArangoDBEngine = engine.await()

suspend fun ArangoDatabaseAsync.existsAsync(): Boolean = exists().await()

suspend fun ArangoDatabaseAsync.getAccessibleDatabasesAsync(): Collection<String> = accessibleDatabases.await()

suspend fun ArangoDatabaseAsync.getIndexAsync(id: String): IndexEntity = getIndex(id).await()

suspend fun ArangoDatabaseAsync.deleteIndexAsync(id: String): String = deleteIndex(id).await()

suspend fun ArangoDatabaseAsync.createAsync(): Boolean = create().await()

suspend fun ArangoDatabaseAsync.dropAsync(): Boolean = drop().await()

suspend fun ArangoDatabaseAsync.grantAccessAsync(user: String, permissions: Permissions) {
    grantAccess(user, permissions).await()
}

suspend fun ArangoDatabaseAsync.grantAccessAsync(user: String) {
    grantAccess(user).await()
}

suspend fun ArangoDatabaseAsync.revokeAccessAsync(user: String) {
    revokeAccess(user).await()
}

suspend fun ArangoDatabaseAsync.resetAccessAsync(user: String) {
    resetAccess(user).await()
}

suspend fun ArangoDatabaseAsync.grantDefaultCollectionAccessAsync(user: String, permissions: Permissions) {
    grantDefaultCollectionAccess(user, permissions).await()
}

suspend fun ArangoDatabaseAsync.getPermissionsAsync(user: String): Permissions = getPermissions(user).await()

suspend fun ArangoDatabaseAsync.parseQueryAsync(query: String): AqlParseEntity = parseQuery(query).await()

suspend fun ArangoDatabaseAsync.clearQueryCacheAsync() {
    clearQueryCache().await()
}

suspend fun ArangoDatabaseAsync.getQueryCachePropertiesAsync(): QueryCachePropertiesEntity = queryCacheProperties.await()

suspend fun ArangoDatabaseAsync.setQueryCachePropertiesAsync(properties: QueryCachePropertiesEntity): QueryCachePropertiesEntity =
    setQueryCacheProperties(properties).await()

suspend fun ArangoDatabaseAsync.getQueryTrackingPropertiesAsync(): QueryTrackingPropertiesEntity = queryTrackingProperties.await()

suspend fun ArangoDatabaseAsync.setQueryTrackingPropertiesAsync(properties: QueryTrackingPropertiesEntity): QueryTrackingPropertiesEntity =
    setQueryTrackingProperties(properties).await()

suspend fun ArangoDatabaseAsync.getCurrentlyRunningQueriesAsync(): Collection<QueryEntity> = currentlyRunningQueries.await()

suspend fun ArangoDatabaseAsync.getSlowQueriesAsync(): Collection<QueryEntity> = slowQueries.await()

suspend fun ArangoDatabaseAsync.clearSlowQueriesAsync() {
    clearSlowQueries().await()
}

suspend fun ArangoDatabaseAsync.killQueryAsync(id: String) {
    killQuery(id).await()
}

suspend fun ArangoDatabaseAsync.getGraphsAsync(): Collection<GraphEntity> = graphs.await()

suspend fun ArangoDatabaseAsync.abortStreamTransactionAsync(id: String): StreamTransactionEntity = abortStreamTransaction(id).await()

suspend fun ArangoDatabaseAsync.getStreamTransactionAsync(id: String): StreamTransactionEntity = getStreamTransaction(id).await()

suspend fun ArangoDatabaseAsync.getStreamTransactionsAsync(): Collection<TransactionEntity> = streamTransactions.await()

suspend fun ArangoDatabaseAsync.commitStreamTransactionAsync(id: String): StreamTransactionEntity = commitStreamTransaction(id).await()

suspend fun ArangoDatabaseAsync.getInfoAsync(): DatabaseEntity = info.await()

suspend fun ArangoDatabaseAsync.reloadRoutingAsync() {
    reloadRouting().await()
}

suspend fun ArangoDatabaseAsync.getViewsAsync(): Collection<ViewEntity> = views.await()

suspend fun ArangoDatabaseAsync.createViewAsync(name: String, type: ViewType): ViewEntity = createView(name, type).await()