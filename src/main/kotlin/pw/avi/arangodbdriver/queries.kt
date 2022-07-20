@file:Suppress("unused")

package pw.avi.arangodbdriver

import com.arangodb.model.AqlQueryExplainOptions
import com.arangodb.model.AqlQueryOptions

sealed class AbstractQueryBuilder<T>(private val ctor: () -> T) {

    private val _bindVars = mutableMapOf<String, Any?>()
    val bindVars: Map<String, Any?> get() = _bindVars

    var options: T? = null
        private set

    fun bindVars(vararg pairs: Pair<String, Any?>) {
        _bindVars += pairs
    }

    fun options(opts: T) {
        options = opts
    }

    fun options(optsBuilder: (T) -> Unit) {
        options = (options ?: ctor()).apply(optsBuilder)
    }

}

class AqlQueryBuilder : AbstractQueryBuilder<AqlQueryOptions>({ AqlQueryOptions() })
class AqlQueryExplainBuilder : AbstractQueryBuilder<AqlQueryExplainOptions>({ AqlQueryExplainOptions() })