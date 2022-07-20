@file:Suppress("unused")

package pw.avi.arangodbdriver

import com.arangodb.ArangoCursor
import com.arangodb.ArangoDatabase
import com.arangodb.entity.*
import com.arangodb.entity.arangosearch.analyzer.SearchAnalyzer
import com.arangodb.model.*
import com.arangodb.model.arangosearch.AnalyzerDeleteOptions
import com.arangodb.model.arangosearch.ArangoSearchCreateOptions

// ==== createCollection ====

inline fun ArangoDatabase.createCollection(name: String, optionsBuilder: (CollectionCreateOptions.() -> Unit)): CollectionEntity =
    createCollection(name, CollectionCreateOptions().apply(optionsBuilder))

// ==== getCollections ====

inline fun ArangoDatabase.getCollections(optionsBuilder: (CollectionsReadOptions.() -> Unit)): MutableCollection<CollectionEntity> =
    getCollections(CollectionsReadOptions().apply(optionsBuilder))

// ==== cursor ====

inline fun <reified T> ArangoDatabase.cursor(cursorId: String): ArangoCursor<T> = cursor(cursorId, T::class.java)

// ==== aql function ====

inline fun ArangoDatabase.createAqlFunction(
    name: String,
    code: String,
    optionsBuilder: (AqlFunctionCreateOptions.() -> Unit)
): Unit = createAqlFunction(name, code, AqlFunctionCreateOptions().apply(optionsBuilder))

inline fun ArangoDatabase.deleteAqlFunction(
    name: String,
    optionsBuilder: (AqlFunctionDeleteOptions.() -> Unit)
): Int = deleteAqlFunction(name, AqlFunctionDeleteOptions().apply(optionsBuilder))

inline fun ArangoDatabase.getAqlFunctions(optionsBuilder: (AqlFunctionGetOptions.() -> Unit)): Collection<AqlFunctionEntity> =
    getAqlFunctions(AqlFunctionGetOptions().apply(optionsBuilder))

// ==== graphs ====

inline fun ArangoDatabase.createGraph(
    name: String,
    edgeDefinitions: Collection<EdgeDefinition>,
    optionsBuilder: (GraphCreateOptions.() -> Unit)
): GraphEntity = createGraph(name, edgeDefinitions, GraphCreateOptions().apply(optionsBuilder))

// ==== transactions ====

inline fun <reified T> ArangoDatabase.transaction(
    action: String,
    optionsBuilder: (TransactionOptions.() -> Unit)
): T = transaction(action, T::class.java, TransactionOptions().apply(optionsBuilder))

inline fun ArangoDatabase.beginStreamTransaction(optionsBuilder: (StreamTransactionOptions.() -> Unit)): StreamTransactionEntity =
    beginStreamTransaction(StreamTransactionOptions().apply(optionsBuilder))

// ==== get document ====

inline fun <reified T> ArangoDatabase.getDocument(
    id: String,
    optionsBuilder: (DocumentReadOptions.() -> Unit)
): T = getDocument(id, T::class.java, DocumentReadOptions().apply(optionsBuilder))

// ==== createArangoSearch ====

inline fun ArangoDatabase.createArangoSearch(
    name: String,
    optionsBuilder: (ArangoSearchCreateOptions.() -> Unit)
): ViewEntity = createArangoSearch(name, ArangoSearchCreateOptions().apply(optionsBuilder))

// ==== analyzers ====

inline fun ArangoDatabase.createSearchAnalyzer(builder: (() -> SearchAnalyzer)): SearchAnalyzer =
    createSearchAnalyzer(builder())

inline fun ArangoDatabase.deleteSearchAnalyzer(
    name: String,
    optionsBuilder: (AnalyzerDeleteOptions.() -> Unit)
): Unit = deleteSearchAnalyzer(name, AnalyzerDeleteOptions().apply(optionsBuilder))

// ==== queries ====

inline fun <reified T> ArangoDatabase.query(
    aql: String,
    builder: AqlQueryBuilder.() -> Unit
): ArangoCursor<T> = with(AqlQueryBuilder()) {
    builder()
    query(aql, bindVars.ifEmpty { null }, options, T::class.java)
}

// ==== explainQuery ====

inline fun ArangoDatabase.explainQuery(
    aql: String,
    builder: AqlQueryExplainBuilder.() -> Unit
): AqlExecutionExplainEntity = with(AqlQueryExplainBuilder()) {
    builder()
    explainQuery(aql, bindVars.ifEmpty { null }, options)
}