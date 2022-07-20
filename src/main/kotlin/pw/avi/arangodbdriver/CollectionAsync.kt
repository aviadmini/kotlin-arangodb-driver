@file:Suppress("unused")

package pw.avi.arangodbdriver

import com.arangodb.async.ArangoCollectionAsync
import com.arangodb.entity.*
import com.arangodb.model.*
import kotlinx.coroutines.future.await

// ==== insert ====

suspend inline fun <T> ArangoCollectionAsync.insertDocumentAsync(
    doc: T,
    optionsBuilder: (DocumentCreateOptions.() -> Unit) = {}
): DocumentCreateEntity<T> = insertDocument(doc, DocumentCreateOptions().apply(optionsBuilder)).await()

suspend inline fun <T> ArangoCollectionAsync.insertDocumentsAsync(
    docs: Collection<T>,
    optionsBuilder: (DocumentCreateOptions.() -> Unit) = {}
): MultiDocumentEntity<DocumentCreateEntity<T>> = insertDocuments(docs, DocumentCreateOptions().apply(optionsBuilder)).await()

// ==== import  ====

suspend inline fun ArangoCollectionAsync.importDocumentsAsync(
    values: Collection<*>,
    optionsBuilder: (DocumentImportOptions.() -> Unit) = {}
): DocumentImportEntity = importDocuments(values, DocumentImportOptions().apply(optionsBuilder)).await()

suspend inline fun ArangoCollectionAsync.importDocumentsAsync(
    values: String,
    optionsBuilder: (DocumentImportOptions.() -> Unit) = {}
): DocumentImportEntity = importDocuments(values, DocumentImportOptions().apply(optionsBuilder)).await()

// ==== get ====

suspend inline fun <reified T> ArangoCollectionAsync.getDocumentAsync(
    key: String,
    optionsBuilder: (DocumentReadOptions.() -> Unit) = {}
): T = getDocument(key, T::class.java, DocumentReadOptions().apply(optionsBuilder)).await()

suspend inline fun <reified T> ArangoCollectionAsync.getDocumentsAsync(
    keys: Collection<String>,
    optionsBuilder: (DocumentReadOptions.() -> Unit) = {}
): MultiDocumentEntity<T> = getDocuments(keys, T::class.java, DocumentReadOptions().apply(optionsBuilder)).await()

// ==== replace ====

suspend inline fun <T> ArangoCollectionAsync.replaceDocumentAsync(
    key: String,
    doc: T,
    optionsBuilder: (DocumentReplaceOptions.() -> Unit) = {}
): DocumentUpdateEntity<T> = replaceDocument(key, doc, DocumentReplaceOptions().apply(optionsBuilder)).await()

suspend inline fun <T> ArangoCollectionAsync.replaceDocumentsAsync(
    docs: Collection<T>,
    optionsBuilder: (DocumentReplaceOptions.() -> Unit) = {}
): MultiDocumentEntity<DocumentUpdateEntity<T>> = replaceDocuments(docs, DocumentReplaceOptions().apply(optionsBuilder)).await()

// ==== update ====

suspend inline fun <T> ArangoCollectionAsync.updateDocumentAsync(
    key: String,
    doc: T,
    optionsBuilder: (DocumentUpdateOptions.() -> Unit) = {}
): DocumentUpdateEntity<T> = updateDocument(key, doc, DocumentUpdateOptions().apply(optionsBuilder)).await()

suspend inline fun <T, reified U> ArangoCollectionAsync.updateDocumentPartialAsync(
    key: String,
    doc: T,
    optionsBuilder: (DocumentUpdateOptions.() -> Unit) = {}
): DocumentUpdateEntity<U> = updateDocument(key, doc, DocumentUpdateOptions().apply(optionsBuilder), U::class.java).await()

suspend inline fun <T> ArangoCollectionAsync.updateDocumentsAsync(
    docs: Collection<T>,
    optionsBuilder: (DocumentUpdateOptions.() -> Unit) = {}
): MultiDocumentEntity<DocumentUpdateEntity<T>> = updateDocuments(docs, DocumentUpdateOptions().apply(optionsBuilder)).await()

suspend inline fun <T, reified U> ArangoCollectionAsync.updateDocumentsPartialAsync(
    docs: Collection<T>,
    optionsBuilder: (DocumentUpdateOptions.() -> Unit) = {}
): MultiDocumentEntity<DocumentUpdateEntity<U>> = updateDocuments(docs, DocumentUpdateOptions().apply(optionsBuilder), U::class.java).await()

// ==== delete ====

suspend inline fun <reified T> ArangoCollectionAsync.deleteDocumentAsync(
    key: String,
    optionsBuilder: (DocumentDeleteOptions.() -> Unit) = {}
): DocumentDeleteEntity<T> = deleteDocument(key, T::class.java, DocumentDeleteOptions().apply(optionsBuilder)).await()

suspend inline fun <reified T> ArangoCollectionAsync.deleteDocumentsAsync(
    docs: Collection<T>,
    optionsBuilder: (DocumentDeleteOptions.() -> Unit) = {}
): MultiDocumentEntity<DocumentDeleteEntity<T>> = deleteDocuments(docs, T::class.java, DocumentDeleteOptions().apply(optionsBuilder)).await()

// ==== exists ====

suspend inline fun ArangoCollectionAsync.documentExistsAsync(
    key: String,
    optionsBuilder: (DocumentExistsOptions.() -> Unit) = {}
): Boolean = documentExists(key, DocumentExistsOptions().apply(optionsBuilder)).await()

// ==== indexes ====

suspend inline fun ArangoCollectionAsync.ensurePersistentIndexAsync(
    fields: Iterable<String>,
    optionsBuilder: (PersistentIndexOptions.() -> Unit) = {}
): IndexEntity = ensurePersistentIndex(fields, PersistentIndexOptions().apply(optionsBuilder)).await()

suspend inline fun ArangoCollectionAsync.ensureGeoIndexAsync(
    fields: Iterable<String>,
    optionsBuilder: (GeoIndexOptions.() -> Unit) = {}
): IndexEntity = ensureGeoIndex(fields, GeoIndexOptions().apply(optionsBuilder)).await()

suspend inline fun ArangoCollectionAsync.ensureFulltextIndexAsync(
    fields: Iterable<String>,
    optionsBuilder: (FulltextIndexOptions.() -> Unit) = {}
): IndexEntity = ensureFulltextIndex(fields, FulltextIndexOptions().apply(optionsBuilder)).await()

suspend inline fun ArangoCollectionAsync.ensureTtlIndexAsync(
    fields: Iterable<String>,
    optionsBuilder: (TtlIndexOptions.() -> Unit) = {}
): IndexEntity = ensureTtlIndex(fields, TtlIndexOptions().apply(optionsBuilder)).await()

// ==== truncate ====

suspend inline fun ArangoCollectionAsync.truncateAsync(optionsBuilder: (CollectionTruncateOptions.() -> Unit) = {}): CollectionEntity =
    truncate(CollectionTruncateOptions().apply(optionsBuilder)).await()

// ==== count ====

suspend inline fun ArangoCollectionAsync.countAsync(optionsBuilder: (CollectionCountOptions.() -> Unit) = {}): CollectionPropertiesEntity =
    count(CollectionCountOptions().apply(optionsBuilder)).await()

// ==== create ====

suspend inline fun ArangoCollectionAsync.createAsync(optionsBuilder: (CollectionCreateOptions.() -> Unit) = {}): CollectionEntity =
    create(CollectionCreateOptions().apply(optionsBuilder)).await()

// ==== create ====

suspend inline fun ArangoCollectionAsync.changePropertiesAsync(optionsBuilder: (CollectionPropertiesOptions.() -> Unit) = {}): CollectionPropertiesEntity =
    changeProperties(CollectionPropertiesOptions().apply(optionsBuilder)).await()

// ==== other functions returning future ====

suspend inline fun ArangoCollectionAsync.getIndexAsync(id: String): IndexEntity = getIndex(id).await()

suspend inline fun ArangoCollectionAsync.deleteIndexAsync(id: String): String = deleteIndex(id).await()

suspend inline fun ArangoCollectionAsync.getIndexesAsync(): Collection<IndexEntity> = indexes.await()

suspend inline fun ArangoCollectionAsync.existsAsync(): Boolean = exists().await()

suspend inline fun ArangoCollectionAsync.dropAsync(isSystem: Boolean = false) {
    drop(isSystem).await()
}

suspend inline fun ArangoCollectionAsync.getInfoAsync(): CollectionEntity = info.await()

suspend inline fun ArangoCollectionAsync.getPropertiesAsync(): CollectionPropertiesEntity = properties.await()

suspend inline fun ArangoCollectionAsync.getResponsibleShardAsync(value: Any): ShardEntity = getResponsibleShard(value).await()

suspend inline fun ArangoCollectionAsync.getRevisionAsync(): CollectionRevisionEntity = revision.await()

suspend inline fun ArangoCollectionAsync.grantAccessAsync(user: String, permissions: Permissions) {
    grantAccess(user, permissions).await()
}

suspend inline fun ArangoCollectionAsync.revokeAccessAsync(user: String) {
    resetAccess(user).await()
}

suspend inline fun ArangoCollectionAsync.resetAccessAsync(user: String) {
    resetAccess(user).await()
}

suspend inline fun ArangoCollectionAsync.getPermissionsAsync(user: String): Permissions = getPermissions(user).await()
