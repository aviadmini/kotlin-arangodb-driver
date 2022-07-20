@file:Suppress("unused")

package pw.avi.arangodbdriver

import com.arangodb.ArangoCollection
import com.arangodb.entity.*
import com.arangodb.model.*

// ==== insert ====

inline fun <T> ArangoCollection.insertDocument(
    doc: T,
    optionsBuilder: (DocumentCreateOptions.() -> Unit) = {}
): DocumentCreateEntity<T> = insertDocument(doc, DocumentCreateOptions().apply(optionsBuilder))

inline fun <T> ArangoCollection.insertDocuments(
    docs: Collection<T>,
    optionsBuilder: (DocumentCreateOptions.() -> Unit) = {}
): MultiDocumentEntity<DocumentCreateEntity<T>> = insertDocuments(docs, DocumentCreateOptions().apply(optionsBuilder))

// ==== import  ====

inline fun ArangoCollection.importDocuments(
    values: Collection<*>,
    optionsBuilder: (DocumentImportOptions.() -> Unit) = {}
): DocumentImportEntity = importDocuments(values, DocumentImportOptions().apply(optionsBuilder))

inline fun ArangoCollection.importDocuments(
    values: String,
    optionsBuilder: (DocumentImportOptions.() -> Unit) = {}
): DocumentImportEntity = importDocuments(values, DocumentImportOptions().apply(optionsBuilder))

// ==== get ====

inline fun <reified T> ArangoCollection.getDocument(
    key: String,
    optionsBuilder: (DocumentReadOptions.() -> Unit) = {}
): T = getDocument(key, T::class.java, DocumentReadOptions().apply(optionsBuilder))

inline fun <reified T> ArangoCollection.getDocuments(
    keys: Collection<String>,
    optionsBuilder: (DocumentReadOptions.() -> Unit) = {}
): MultiDocumentEntity<T> = getDocuments(keys, T::class.java, DocumentReadOptions().apply(optionsBuilder))

// ==== replace ====

inline fun <T> ArangoCollection.replaceDocument(
    key: String,
    doc: T,
    optionsBuilder: (DocumentReplaceOptions.() -> Unit) = {}
): DocumentUpdateEntity<T> = replaceDocument(key, doc, DocumentReplaceOptions().apply(optionsBuilder))

inline fun <T> ArangoCollection.replaceDocuments(
    docs: Collection<T>,
    optionsBuilder: (DocumentReplaceOptions.() -> Unit) = {}
): MultiDocumentEntity<DocumentUpdateEntity<T>> = replaceDocuments(docs, DocumentReplaceOptions().apply(optionsBuilder))

// ==== update ====

inline fun <T> ArangoCollection.updateDocument(
    key: String,
    doc: T,
    optionsBuilder: (DocumentUpdateOptions.() -> Unit) = {}
): DocumentUpdateEntity<T> = updateDocument(key, doc, DocumentUpdateOptions().apply(optionsBuilder))

inline fun <T, reified U> ArangoCollection.updateDocumentPartial(
    key: String,
    doc: T,
    optionsBuilder: (DocumentUpdateOptions.() -> Unit) = {}
): DocumentUpdateEntity<U> = updateDocument(key, doc, DocumentUpdateOptions().apply(optionsBuilder), U::class.java)

inline fun <T> ArangoCollection.updateDocuments(
    docs: Collection<T>,
    optionsBuilder: (DocumentUpdateOptions.() -> Unit) = {}
): MultiDocumentEntity<DocumentUpdateEntity<T>> = updateDocuments(docs, DocumentUpdateOptions().apply(optionsBuilder))

inline fun <T, reified U> ArangoCollection.updateDocumentsPartial(
    docs: Collection<T>,
    optionsBuilder: (DocumentUpdateOptions.() -> Unit) = {}
): MultiDocumentEntity<DocumentUpdateEntity<U>> = updateDocuments(docs, DocumentUpdateOptions().apply(optionsBuilder), U::class.java)

// ==== delete ====

inline fun <reified T> ArangoCollection.deleteDocument(
    key: String,
    optionsBuilder: (DocumentDeleteOptions.() -> Unit) = {}
): DocumentDeleteEntity<T> = deleteDocument(key, T::class.java, DocumentDeleteOptions().apply(optionsBuilder))

inline fun <reified T> ArangoCollection.deleteDocuments(
    docs: Collection<T>,
    optionsBuilder: (DocumentDeleteOptions.() -> Unit) = {}
): MultiDocumentEntity<DocumentDeleteEntity<T>> = deleteDocuments(docs, T::class.java, DocumentDeleteOptions().apply(optionsBuilder))

// ==== exists ====

inline fun ArangoCollection.documentExists(
    key: String,
    optionsBuilder: (DocumentExistsOptions.() -> Unit) = {}
): Boolean = documentExists(key, DocumentExistsOptions().apply(optionsBuilder))

// ==== indexes ====

inline fun ArangoCollection.ensurePersistentIndex(
    fields: Iterable<String>,
    optionsBuilder: (PersistentIndexOptions.() -> Unit) = {}
): IndexEntity = ensurePersistentIndex(fields, PersistentIndexOptions().apply(optionsBuilder))

inline fun ArangoCollection.ensureGeoIndex(
    fields: Iterable<String>,
    optionsBuilder: (GeoIndexOptions.() -> Unit) = {}
): IndexEntity = ensureGeoIndex(fields, GeoIndexOptions().apply(optionsBuilder))

inline fun ArangoCollection.ensureFulltextIndex(
    fields: Iterable<String>,
    optionsBuilder: (FulltextIndexOptions.() -> Unit) = {}
): IndexEntity = ensureFulltextIndex(fields, FulltextIndexOptions().apply(optionsBuilder))

inline fun ArangoCollection.ensureTtlIndex(
    fields: Iterable<String>,
    optionsBuilder: (TtlIndexOptions.() -> Unit) = {}
): IndexEntity = ensureTtlIndex(fields, TtlIndexOptions().apply(optionsBuilder))

// ==== truncate ====

inline fun ArangoCollection.truncate(optionsBuilder: (CollectionTruncateOptions.() -> Unit) = {}): CollectionEntity =
    truncate(CollectionTruncateOptions().apply(optionsBuilder))

// ==== count ====

inline fun ArangoCollection.count(optionsBuilder: (CollectionCountOptions.() -> Unit) = {}): CollectionPropertiesEntity =
    count(CollectionCountOptions().apply(optionsBuilder))

// ==== create ====

inline fun ArangoCollection.create(optionsBuilder: (CollectionCreateOptions.() -> Unit) = {}): CollectionEntity =
    create(CollectionCreateOptions().apply(optionsBuilder))

// ==== create ====

inline fun ArangoCollection.changeProperties(optionsBuilder: (CollectionPropertiesOptions.() -> Unit) = {}): CollectionPropertiesEntity =
    changeProperties(CollectionPropertiesOptions().apply(optionsBuilder))
