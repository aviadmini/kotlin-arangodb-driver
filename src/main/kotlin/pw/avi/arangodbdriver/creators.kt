@file:Suppress("unused")

package pw.avi.arangodbdriver

import com.arangodb.ArangoDB
import com.arangodb.async.ArangoDBAsync

fun arangoDb(builder: ArangoDB.Builder.() -> Unit): ArangoDB = ArangoDB.Builder().apply(builder).build()

fun arangoDbAsync(builder: ArangoDBAsync.Builder.() -> Unit): ArangoDBAsync = ArangoDBAsync.Builder().apply(builder).build()

