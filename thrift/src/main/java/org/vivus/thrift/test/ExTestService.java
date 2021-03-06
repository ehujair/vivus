/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.vivus.thrift.test;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExTestService {

  public interface Iface extends TestService.Iface {

    public String msgFunc() throws TestException, ExTestException, org.apache.thrift.TException;

  }

  public interface AsyncIface extends TestService .AsyncIface {

    public void msgFunc(org.apache.thrift.async.AsyncMethodCallback<AsyncClient.msgFunc_call> resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends TestService.Client implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public String msgFunc() throws TestException, ExTestException, org.apache.thrift.TException
    {
      send_msgFunc();
      return recv_msgFunc();
    }

    public void send_msgFunc() throws org.apache.thrift.TException
    {
      msgFunc_args args = new msgFunc_args();
      sendBase("msgFunc", args);
    }

    public String recv_msgFunc() throws TestException, ExTestException, org.apache.thrift.TException
    {
      msgFunc_result result = new msgFunc_result();
      receiveBase(result, "msgFunc");
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.te != null) {
        throw result.te;
      }
      if (result.ete != null) {
        throw result.ete;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "msgFunc failed: unknown result");
    }

  }
  public static class AsyncClient extends TestService.AsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void msgFunc(org.apache.thrift.async.AsyncMethodCallback<msgFunc_call> resultHandler) throws org.apache.thrift.TException {
      checkReady();
      msgFunc_call method_call = new msgFunc_call(resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class msgFunc_call extends org.apache.thrift.async.TAsyncMethodCall {
      public msgFunc_call(org.apache.thrift.async.AsyncMethodCallback<msgFunc_call> resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("msgFunc", org.apache.thrift.protocol.TMessageType.CALL, 0));
        msgFunc_args args = new msgFunc_args();
        args.write(prot);
        prot.writeMessageEnd();
      }

      public String getResult() throws TestException, ExTestException, org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_msgFunc();
      }
    }

  }

  public static class Processor<I extends Iface> extends TestService.Processor<I> implements org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("msgFunc", new msgFunc());
      return processMap;
    }

    public static class msgFunc<I extends Iface> extends org.apache.thrift.ProcessFunction<I, msgFunc_args> {
      public msgFunc() {
        super("msgFunc");
      }

      public msgFunc_args getEmptyArgsInstance() {
        return new msgFunc_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public msgFunc_result getResult(I iface, msgFunc_args args) throws org.apache.thrift.TException {
        msgFunc_result result = new msgFunc_result();
        try {
          result.success = iface.msgFunc();
        } catch (TestException te) {
          result.te = te;
        } catch (ExTestException ete) {
          result.ete = ete;
        }
        return result;
      }
    }

  }

  public static class msgFunc_args implements org.apache.thrift.TBase<msgFunc_args, msgFunc_args._Fields>, java.io.Serializable, Cloneable   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("msgFunc_args");


    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new msgFunc_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new msgFunc_argsTupleSchemeFactory());
    }


    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
;

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(msgFunc_args.class, metaDataMap);
    }

    public msgFunc_args() {
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public msgFunc_args(msgFunc_args other) {
    }

    public msgFunc_args deepCopy() {
      return new msgFunc_args(this);
    }

    @Override
    public void clear() {
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof msgFunc_args)
        return this.equals((msgFunc_args)that);
      return false;
    }

    public boolean equals(msgFunc_args that) {
      if (that == null)
        return false;

      return true;
    }

    @Override
    public int hashCode() {
      return 0;
    }

    public int compareTo(msgFunc_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      msgFunc_args typedOther = (msgFunc_args)other;

      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("msgFunc_args(");
      boolean first = true;

      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class msgFunc_argsStandardSchemeFactory implements SchemeFactory {
      public msgFunc_argsStandardScheme getScheme() {
        return new msgFunc_argsStandardScheme();
      }
    }

    private static class msgFunc_argsStandardScheme extends StandardScheme<msgFunc_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, msgFunc_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, msgFunc_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class msgFunc_argsTupleSchemeFactory implements SchemeFactory {
      public msgFunc_argsTupleScheme getScheme() {
        return new msgFunc_argsTupleScheme();
      }
    }

    private static class msgFunc_argsTupleScheme extends TupleScheme<msgFunc_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, msgFunc_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, msgFunc_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
      }
    }

  }

  public static class msgFunc_result implements org.apache.thrift.TBase<msgFunc_result, msgFunc_result._Fields>, java.io.Serializable, Cloneable   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("msgFunc_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRING, (short)0);
    private static final org.apache.thrift.protocol.TField TE_FIELD_DESC = new org.apache.thrift.protocol.TField("te", org.apache.thrift.protocol.TType.STRUCT, (short)-1);
    private static final org.apache.thrift.protocol.TField ETE_FIELD_DESC = new org.apache.thrift.protocol.TField("ete", org.apache.thrift.protocol.TType.STRUCT, (short)-2);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new msgFunc_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new msgFunc_resultTupleSchemeFactory());
    }

    public String success; // required
    public TestException te; // required
    public ExTestException ete; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success"),
      TE((short)-1, "te"),
      ETE((short)-2, "ete");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 0: // SUCCESS
            return SUCCESS;
          case -1: // TE
            return TE;
          case -2: // ETE
            return ETE;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      tmpMap.put(_Fields.TE, new org.apache.thrift.meta_data.FieldMetaData("te", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT)));
      tmpMap.put(_Fields.ETE, new org.apache.thrift.meta_data.FieldMetaData("ete", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(msgFunc_result.class, metaDataMap);
    }

    public msgFunc_result() {
    }

    public msgFunc_result(
      String success,
      TestException te,
      ExTestException ete)
    {
      this();
      this.success = success;
      this.te = te;
      this.ete = ete;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public msgFunc_result(msgFunc_result other) {
      if (other.isSetSuccess()) {
        this.success = other.success;
      }
      if (other.isSetTe()) {
        this.te = new TestException(other.te);
      }
      if (other.isSetEte()) {
        this.ete = new ExTestException(other.ete);
      }
    }

    public msgFunc_result deepCopy() {
      return new msgFunc_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
      this.te = null;
      this.ete = null;
    }

    public String getSuccess() {
      return this.success;
    }

    public msgFunc_result setSuccess(String success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public TestException getTe() {
      return this.te;
    }

    public msgFunc_result setTe(TestException te) {
      this.te = te;
      return this;
    }

    public void unsetTe() {
      this.te = null;
    }

    /** Returns true if field te is set (has been assigned a value) and false otherwise */
    public boolean isSetTe() {
      return this.te != null;
    }

    public void setTeIsSet(boolean value) {
      if (!value) {
        this.te = null;
      }
    }

    public ExTestException getEte() {
      return this.ete;
    }

    public msgFunc_result setEte(ExTestException ete) {
      this.ete = ete;
      return this;
    }

    public void unsetEte() {
      this.ete = null;
    }

    /** Returns true if field ete is set (has been assigned a value) and false otherwise */
    public boolean isSetEte() {
      return this.ete != null;
    }

    public void setEteIsSet(boolean value) {
      if (!value) {
        this.ete = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((String)value);
        }
        break;

      case TE:
        if (value == null) {
          unsetTe();
        } else {
          setTe((TestException)value);
        }
        break;

      case ETE:
        if (value == null) {
          unsetEte();
        } else {
          setEte((ExTestException)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case TE:
        return getTe();

      case ETE:
        return getEte();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case TE:
        return isSetTe();
      case ETE:
        return isSetEte();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof msgFunc_result)
        return this.equals((msgFunc_result)that);
      return false;
    }

    public boolean equals(msgFunc_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_te = true && this.isSetTe();
      boolean that_present_te = true && that.isSetTe();
      if (this_present_te || that_present_te) {
        if (!(this_present_te && that_present_te))
          return false;
        if (!this.te.equals(that.te))
          return false;
      }

      boolean this_present_ete = true && this.isSetEte();
      boolean that_present_ete = true && that.isSetEte();
      if (this_present_ete || that_present_ete) {
        if (!(this_present_ete && that_present_ete))
          return false;
        if (!this.ete.equals(that.ete))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return 0;
    }

    public int compareTo(msgFunc_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      msgFunc_result typedOther = (msgFunc_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(typedOther.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, typedOther.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetTe()).compareTo(typedOther.isSetTe());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetTe()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.te, typedOther.te);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetEte()).compareTo(typedOther.isSetEte());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetEte()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ete, typedOther.ete);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
      }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("msgFunc_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("te:");
      if (this.te == null) {
        sb.append("null");
      } else {
        sb.append(this.te);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ete:");
      if (this.ete == null) {
        sb.append("null");
      } else {
        sb.append(this.ete);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class msgFunc_resultStandardSchemeFactory implements SchemeFactory {
      public msgFunc_resultStandardScheme getScheme() {
        return new msgFunc_resultStandardScheme();
      }
    }

    private static class msgFunc_resultStandardScheme extends StandardScheme<msgFunc_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, msgFunc_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.success = iprot.readString();
                struct.setSuccessIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case -1: // TE
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.te = new TestException();
                struct.te.read(iprot);
                struct.setTeIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case -2: // ETE
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.ete = new ExTestException();
                struct.ete.read(iprot);
                struct.setEteIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, msgFunc_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.ete != null) {
          oprot.writeFieldBegin(ETE_FIELD_DESC);
          struct.ete.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.te != null) {
          oprot.writeFieldBegin(TE_FIELD_DESC);
          struct.te.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          oprot.writeString(struct.success);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class msgFunc_resultTupleSchemeFactory implements SchemeFactory {
      public msgFunc_resultTupleScheme getScheme() {
        return new msgFunc_resultTupleScheme();
      }
    }

    private static class msgFunc_resultTupleScheme extends TupleScheme<msgFunc_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, msgFunc_result struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        if (struct.isSetTe()) {
          optionals.set(1);
        }
        if (struct.isSetEte()) {
          optionals.set(2);
        }
        oprot.writeBitSet(optionals, 3);
        if (struct.isSetSuccess()) {
          oprot.writeString(struct.success);
        }
        if (struct.isSetTe()) {
          struct.te.write(oprot);
        }
        if (struct.isSetEte()) {
          struct.ete.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, msgFunc_result struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(3);
        if (incoming.get(0)) {
          struct.success = iprot.readString();
          struct.setSuccessIsSet(true);
        }
        if (incoming.get(1)) {
          struct.te = new TestException();
          struct.te.read(iprot);
          struct.setTeIsSet(true);
        }
        if (incoming.get(2)) {
          struct.ete = new ExTestException();
          struct.ete.read(iprot);
          struct.setEteIsSet(true);
        }
      }
    }

  }

}
